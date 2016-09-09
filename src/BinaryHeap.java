
public class BinaryHeap implements PriorityQueue {
	
	private Double[] data;
	private int size;
	
	public BinaryHeap() {
		data = new Double[1000000];
		size = 0;
	}
	
	/*
	 * (non-Javadoc)
	 * @see PriorityQueue#isEmpty()
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/*
	 * (non-Javadoc)
	 * @see PriorityQueue#size()
	 */
	public int size() {
		return size;
	}
	
	/*
	 * (non-Javadoc)
	 * @see PriorityQueue#findMin()
	 */
	public double findMin() {
		if (isEmpty()) {
			throw new EmptyPQException();
		}
		return data[0];
	}
	
	/*
	 * (non-Javadoc)
	 * @see PriorityQueue#insert(double)
	 */
	public void insert(double x) {
		data[size] = x;
		int index = size;
		while (data[index] < data[(index-1)/2]) {
			swap(index, (index-1)/2);
			index = (index-1)/2;
		}
		size++;
		if (size == data.length) {
			resize();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see PriorityQueue#deleteMin()
	 */
	public double deleteMin() {
		if (isEmpty()) {
			throw new EmptyPQException();
		}
		double toReturn = data[0];
		swap(0, size-1);
		data[size-1] = null;
		int index = 0;
		int childIndex = minChildIndex(index);
		while (childIndex < data.length && data[childIndex] != null && data[minChildIndex(index)] < data[index]) {
			swap(childIndex, index);
			index = childIndex;
			childIndex = minChildIndex(index);
		}
		size--;
		return toReturn;
	}
	
	/*
	 * (non-Javadoc)
	 * @see PriorityQueue#makeEmpty()
	 */
	public void makeEmpty() {
		size = 0;
		for (int i = 0; i < data.length; i++) {
			data[i] = null;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < data.length; i++) {
			if (data[i] == null) {
				break;
			}
			if (i != 0) {
				sb.append(", ");
			}
			sb.append(data[i]);
		}
		sb.append("]");
		return sb.toString();
	}
	
	/*
	 * Checks to see if the priority queue is valid (parent is less than child)
	 * 
	 * @return true if all parents are less than their children
	 */
	public boolean checkValid() {
		for (int i = 0; i < size; i++) {
			if ((i+1)*2-1 < size && data[i] > data[(i+1)*2-1] || (i+1)*2 < size && data[i] > data[(i+1)*2]) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Doubles the size of the data array
	 */
	private void resize() {
		Double[] bigger = new Double[data.length*2];
		for (int i = 0; i < data.length; i++) {
			bigger[i] = data[i];
		}
		data = bigger;
	}
	
	/*
	 * Swaps the data at index i1 and i2
	 * 
	 * @param i1 the first index
	 * @param i2 the second index
	 */
	private void swap(int i1, int i2) {
		Double temp = data[i1];
		data[i1] = data[i2];
		data[i2] = temp;
	}
	
	/*
	 * Returns the index of the smallest child of the node at index i
	 * 
	 * @param i the index to return the smallest child of
	 * 
	 * @return the index of the smallest child (returns (i+1)*2-1 if there is no child)
	 */
	private int minChildIndex(int i) {
		if ((i+1)*2-1 >= data.length || (i+1)*2 >= data.length) {
			return (i+1)*2-1;
		}
		Double one = data[(i+1)*2-1];
		Double two = data[(i+1)*2];
		if (two == null || one == null) {
			return (i+1)*2-1;
		} else {
			return one > two ? (i+1)*2 : (i+1)*2-1;
		}
	}
}
