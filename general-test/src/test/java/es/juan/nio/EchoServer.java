package es.juan.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Servidor multi-eco que levanta una sola hebra y realiza las lecturas
 * a traves del paquete NIO 
 * @author  jfcorugedo
 */
public class EchoServer {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		//Create TCP server channel
		ServerSocketChannel serv = ServerSocketChannel.open();
		ServerSocket sock = serv.socket();
		
		//Create a socket on your IP and port (i.e: localhost:12345)
		SocketAddress addr = new InetSocketAddress(12345);
		
		//Bind server socket and socket address
		sock.bind(addr);
		
		//Configure socket so all its methods won't be blocking
		serv.configureBlocking(false);
		
		//Create a selector to attend all the incoming requests
		Selector selector = Selector.open();
		
		//Register into the selector the accept request type
		serv.register(selector,SelectionKey.OP_ACCEPT);
		
		//Create a common buffer
		ByteBuffer commonBuffer = ByteBuffer.allocate(10000);
		commonBuffer.clear();
		
		Iterator<SelectionKey> it = null;
		ByteBuffer channelBuffer  = null;
		for (;;){ //Infinite loop
			System.out.println("Waiting for events......");
			selector.select(); // This call do is blocking
			System.out.println("New event received");
			for(SelectionKey selectionKey : selector.selectedKeys()) {
				System.out.println(String.format("Type: %s. Is valid: %b, is acceptable: %b, can be read: %b, can be written: %b, can be connected: %b", selectionKey, selectionKey.isValid(), selectionKey.isAcceptable(), selectionKey.isReadable(), selectionKey.isWritable(), selectionKey.isConnectable()));
			}
			it = selector.selectedKeys().iterator();
			
			while(it.hasNext()) {
				SelectionKey key = (SelectionKey) it.next();
				System.out.println(String.format("Processing %s", key));
				it.remove(); // borro para evitar nueva notificaciÛn
				
				try{
					if (key.isAcceptable()) {
						System.out.println("Received new connection request");
						processConnectionRequest(serv, selector);
					}else if (key.isReadable()) {
						System.out.println("Received new reading request");
						processReadingRequest(selector, commonBuffer, key);
					}else if (key.isWritable()) {
						System.out.println("Received new writing request");
						processWritingRequest(key);
					}
				}catch(Exception e){
					key.cancel();
					try {
						key.channel().close();
					} catch (Exception ce) {}
				}//end catch
			}//end while
		}//end for
	}//end main

	private static void processWritingRequest(SelectionKey key) throws IOException {
		SocketChannel cli = (SocketChannel) key.channel();
		ByteBuffer buf = (ByteBuffer) key.attachment();
		
		System.out.println(String.format("Wrinting into the channel %s", cli));
		buf.flip();//prepare the buffer
		buf.rewind();
		cli.write(buf);
		
		if (buf.hasRemaining()) {
		    //If there is more content remaining, compact the buffer 	
			buf.compact();
		} else {
			buf.clear();
			key.interestOps(SelectionKey.OP_READ);
		}
	}

	private static void processReadingRequest(Selector selector, ByteBuffer commonBuffer, SelectionKey key)
			throws IOException {
		SocketChannel cli = (SocketChannel) key.channel();
		
		
		if (cli.read(commonBuffer) == -1) {
			System.out.println(String.format("Closing channel %s", cli));
			cli.close(); // internally calls key.cancel()
		}
		else {//Send the data to all the channels
			
			commonBuffer.flip();//prepare the buffer
			Iterator<SelectionKey> it2 = selector.keys().iterator();
			System.out.println("Writing data to all the channels");
			SelectionKey keys = null;
			while(it2.hasNext()) {
				keys = it2.next();
				System.out.println(String.format("Writing in %s", keys));
				
				ByteBuffer buf = (ByteBuffer) keys.attachment();
				
				if(buf!=null)
				{
				buf.put(commonBuffer);
				keys.interestOps(SelectionKey.OP_WRITE|SelectionKey.OP_READ);
				
				commonBuffer.rewind();
				}
				
			}
			commonBuffer.clear();
			
		}
	}

	private static void processConnectionRequest(ServerSocketChannel serv, Selector selector)
			throws IOException, ClosedChannelException {
		ByteBuffer channelBuffer;
		SocketChannel cli = serv.accept();
		cli.configureBlocking(false);
		channelBuffer = ByteBuffer.allocate(10000);
		System.out.println(String.format("Registering new reading channel: %s", cli));
		cli.register(selector, SelectionKey.OP_READ, channelBuffer);
	}
}
