package queue;

public class Main {

	public static void main(String[] args) throws Exception {
		MyPriorityQueue<Integer> q = new MyPriorityQueue();
		q.add(1);
		q.add(0);
		
		System.out.println(q.poll());
	}

}
