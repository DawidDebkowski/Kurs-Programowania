//Przerwany program dziala normalnie.

class ChildThread1 extends Thread {
	public void run()
	{
		try {
			for (int i = 0; i < 20; i++) {
				System.out.println("Wywolanie watku dziecka" + i);
				Thread.sleep(100);
			}
		} catch (InterruptedException e) {
			System.out.println("exc");
			e.printStackTrace();
		}
	}
}

class Interrupt1 {
	public static void main(String[] args)
			throws InterruptedException
	{
		ChildThread1 thread = new ChildThread1();
		thread.start();

		// Glowny watek nie wywoluje przerwanie watku dziecka
		thread.interrupt();

	}
}
