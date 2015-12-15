package org.jgrapht.graph;

/**
 * Better implementation of DefaultEdge class, providing much better encapsulation
 * 
 * @author jfcorugedo
 *
 */
public class Edge extends DefaultEdge {

	private static final long serialVersionUID = 1L;

	public Edge() {
		
	}
	
	public Edge(String source, String target) {
		this.source = source;
		this.target = target;
	}
	
	public String getSource() {
		
		return (String) source;
	}
	
	public String getTarget() {
		
		return (String) target;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (target == null) {
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("(%s -> %s)", source, target);
	}
}
