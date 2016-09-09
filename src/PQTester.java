import static java.lang.System.out;

public class PQTester {
	
	/*
	 * The N of the N-Heap
	 */
	private static final int N = 20;
	
	public static void main(String[] args) {
		PQTester test = new PQTester();
		test.testFunctions();
		test.timeAdd(250000);
		test.timeAdd(500000);
		test.timeAdd(750000);
		test.timeAdd(1000000);
		test.timeRemove(25000);
		test.timeRemove(50000);
		test.timeRemove(75000);
		test.timeRemove(100000);
		out.println("----------");
		out.println("Finished testing");
	}
	
	/*
	 * Tests the functions of the priority queues
	 */
	public void testFunctions() {
		out.println("----------");
		out.println("Testing functions of the priority queues");
		out.println("----------");
		// Binary Heap testing
		BinaryHeap bh = new BinaryHeap();
		if (bh.isEmpty()) {
			out.printf("Binary Heap is empty with %d elements%n", bh.size());
		} else {
			out.printf("Binary Heap is not empty with %d elements%n", bh.size());
		}
		bh.insert(0);
		bh.insert(0);
		if (bh.isEmpty()) {
			out.printf("Binary Heap is empty with %d elements%n", bh.size());
		} else {
			out.printf("Binary Heap is not empty with %d elements%n", bh.size());
		}
		bh.makeEmpty();
		for (int i = 0; i < 100; i++) {
			bh.insert(Math.floor(Math.random()*100000));
		}
		if (bh.checkValid()) {
			out.println("Binary Heap valid after adding 100 elements");
		} else {
			out.println("Binary Heap NOT valid after adding 100 elements");
			out.println(bh);
		}
		for (int i = 0; i < 10; i++) {
			bh.deleteMin();
		}
		if (bh.checkValid()) {
			out.println("Binary Heap valid after deleting 10 elements");
		} else {
			out.println("Binary Heap NOT valid after deleting 10 elements");
			out.println(bh);
		}
		bh.makeEmpty();
		try {
			bh.deleteMin();
		} catch (EmptyPQException e) {
			out.println("Binary Heap threw exception when trying to delete from empty PQ");
		}
		try {
			bh.findMin();
		} catch (EmptyPQException e) {
			out.println("Binary Heap threw exception when trying to find min from empty PQ");
		}

		//Three Heap testing
		ThreeHeap th = new ThreeHeap();
		if (th.isEmpty()) {
			out.printf("Three Heap is empty with %d elements%n", th.size());
		} else {
			out.printf("Three Heap is not empty with %d elements%n", th.size());
		}
		th.insert(0);
		th.insert(0);
		if (th.isEmpty()) {
			out.printf("Three Heap is empty with %d elements%n", th.size());
		} else {
			out.printf("Three Heap is not empty with %d elements%n", th.size());
		}
		th.makeEmpty();
		for (int i = 0; i < 100; i++) {
			th.insert(Math.floor(Math.random()*100000));
		}
		if (th.checkValid()) {
			out.println("Three Heap valid after adding 100 elements");
		} else {
			out.println("Three Heap NOT valid after adding 100 elements");
			out.println(th);
		}
		for (int i = 0; i < 10; i++) {
			th.deleteMin();
		}
		if (th.checkValid()) {
			out.println("Three Heap valid after deleting 10 elements");
		} else {
			out.println("Three Heap NOT valid after deleting 10 elements");
			out.println(th);
		}
		th.makeEmpty();
		try {
			th.deleteMin();
		} catch (EmptyPQException e) {
			out.println("Three Heap threw exception when trying to delete from empty PQ");
		}
		try {
			th.findMin();
		} catch (EmptyPQException e) {
			out.println("Three Heap threw exception when trying to find min from empty PQ");
		}

		//N-Heap testing
		MyPQ mp = new MyPQ(N);
		if (mp.isEmpty()) {
			out.printf("%d-Heap is empty with %d elements%n", mp.getN(), mp.size());
		} else {
			out.printf("%d-Heap is not empty with %d elements%n", mp.getN(), mp.size());
		}
		mp.insert(0);
		mp.insert(0);
		if (mp.isEmpty()) {
			out.printf("%d-Heap is empty with %d elements%n", mp.getN(), mp.size());
		} else {
			out.printf("%d-Heap is not empty with %d elements%n", mp.getN(), mp.size());
		}
		mp.makeEmpty();
		for (int i = 0; i < 100; i++) {
			mp.insert(Math.floor(Math.random()*100000));
		}
		if (mp.checkValid()) {
			out.printf("%d-Heap valid after adding 100 elements%n", mp.getN());
		} else {
			out.printf("%d-Heap NOT valid after adding 100 elements%n", mp.getN());
			out.println(mp);
		}
		for (int i = 0; i < 10; i++) {
			mp.deleteMin();
		}
		if (mp.checkValid()) {
			out.printf("%d-Heap valid after deleting 10 elements%n", mp.getN());
		} else {
			out.printf("%d-Heap NOT valid after deleting 10 elements%n", mp.getN());
			out.println(mp);
		}
		mp.makeEmpty();
		try {
			mp.deleteMin();
		} catch (EmptyPQException e) {
			out.printf("%d-Heap threw exception when trying to delete from empty PQ%n", mp.getN());
		}
		try {
			mp.findMin();
		} catch (EmptyPQException e) {
			out.printf("%d-Heap threw exception when trying to find min from empty PQ%n", mp.getN());
		}
		
		out.println();
	}
	
	/*
	 * Times the add functions of all the priority queues
	 * 
	 * @param num the number of elements to add for each test
	 */
	public void timeAdd(int num) {
		out.println("----------");
		out.printf("Timing the add functions (%,d elements)%n", num);
		out.println("----------");
		int numTestCases = 30;
		double average = 0;
		BinaryHeap bh = new BinaryHeap();
		out.print("Running test case ");
		for (int t = 0; t < numTestCases; t++) {
			out.print(t+1 + " ");
			long bhStartTime = System.nanoTime();
			for (int i = 0; i < num; i++) {
				bh.insert(Math.floor(Math.random()*1000000000));
			}
			average += (System.nanoTime()-bhStartTime)/1000000.0;
			bh.makeEmpty();
		}
		out.printf("%nAverage time for Binary Heap to add %,d elements: %.5f miliseconds%n", num, average/numTestCases);
		

		ThreeHeap th = new ThreeHeap();
		average = 0;
		out.print("Running test case ");
		for (int t = 0; t < numTestCases; t++) {
			out.print(t+1 + " ");
			long thStartTime = System.nanoTime();
			for (int i = 0; i < num; i++) {
				th.insert(Math.floor(Math.random()*1000000000));
			}
			average += (System.nanoTime()-thStartTime)/1000000.0;
			th.makeEmpty();
		}
		out.printf("%nAverage time for Three Heap to add %,d elements: %.5f miliseconds%n", num, average/numTestCases);
		
		MyPQ mp = new MyPQ(N);
		average = 0;
		out.print("Running test case ");
		for (int t = 0; t < numTestCases; t++) {
			out.print(t+1 + " ");
			long mpStartTime = System.nanoTime();
			for (int i = 0; i < num; i++) {
				mp.insert(Math.floor(Math.random()*1000000000));
			}
			average += (System.nanoTime()-mpStartTime)/1000000.0;
			mp.makeEmpty();
		}
		out.printf("%nAverage time for %d-Heap to add %,d elements: %.5f miliseconds%n", mp.getN(), num, average/numTestCases);
		out.println();
	}
	
	/*
	 * Times the remove functions of the priority queues
	 * 
	 * @param num the number of elements to add then delete
	 */
	public void timeRemove(int num) {
		out.println("----------");
		out.printf("Timing the delete functions (%,d elements)%n", num);
		out.println("----------");
		int numTestCases = 30;
		double average = 0;
		BinaryHeap bh = new BinaryHeap();
		out.print("Running test case ");
		for (int t = 0; t < numTestCases; t++) {
			out.print(t+1 + " ");
			for (int i = 0; i < num; i++) {
				bh.insert(Math.floor(Math.random()*1000000000));
			}
			long bhStartTime = System.nanoTime();
			while (!bh.isEmpty()) {
				bh.deleteMin();
			}
			average += (System.nanoTime()-bhStartTime)/1000000.0;
			bh.makeEmpty();
		}
		out.printf("%nAverage time for Binary Heap to delete %,d elements: %.5f miliseconds%n", num, average/numTestCases);
		

		ThreeHeap th = new ThreeHeap();
		average = 0;
		out.print("Running test case ");
		for (int t = 0; t < numTestCases; t++) {
			out.print(t+1 + " ");
			for (int i = 0; i < num; i++) {
				th.insert(Math.floor(Math.random()*1000000000));
			}
			long thStartTime = System.nanoTime();
			while (!th.isEmpty()) {
				th.deleteMin();
			}
			average += (System.nanoTime()-thStartTime)/1000000.0;
			th.makeEmpty();
		}
		out.printf("%nAverage time for Three Heap to delete %,d elements: %.5f miliseconds%n", num, average/numTestCases);
		
		MyPQ mp = new MyPQ(N);
		average = 0;
		out.print("Running test case ");
		for (int t = 0; t < numTestCases; t++) {
			out.print(t+1 + " ");
			for (int i = 0; i < num; i++) {
				mp.insert(Math.floor(Math.random()*1000000000));
			}
			long mpStartTime = System.nanoTime();
			while (!mp.isEmpty()) {
				mp.deleteMin();
			}
			average += (System.nanoTime()-mpStartTime)/1000000.0;
			mp.makeEmpty();
		}
		out.printf("%nAverage time for %d-Heap to delete %,d elements: %.5f miliseconds%n", mp.getN(), num, average/numTestCases);
		out.println();
	}
}
