package com.ravi.semaphore;
/*
 * A semaphore controls access to a shared resource through the use of a counter. 
 * If the counter is greater than zero, then access is allowed. 
 * If it is zero, then access is denied. 
 * What the counter is counting are permits that allow access to the shared resource. 
 * Thus, to access the resource, a thread must be granted a permit from the semaphore.
 * 
 * Working of semaphore:
 * In general, to use a semaphore, the thread that wants access to the shared resource tries to acquire a permit.
 * If the semaphore’s count is greater than zero, then the thread acquires a permit, which causes the semaphore’s count to be decremented.
 * Otherwise, the thread will be blocked until a permit can be acquired.
 * When the thread no longer needs an access to the shared resource, it releases the permit, which causes the semaphore’s count to be incremented.
 * If there is another thread waiting for a permit, then that thread will acquire a permit at that time.
 */

/*
 * Constructors:
 * Semaphore(int num)
 * Semaphore(int num, boolean how)
 * 
 * num: number of threads that can access the resource
 */
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {


	public static void main(String[] args) throws InterruptedException {
		// creating a Semaphore object 
        // with number of permits 1 
        Semaphore sem = new Semaphore(1); 
          
        // creating two threads with name A and B 
        // Note that thread A will increment the count 
        // and thread B will decrement the count 
        MyThread mt1 = new MyThread(sem, "A"); 
        MyThread mt2 = new MyThread(sem, "B"); 
          
        // stating threads A and B 
        mt1.start(); 
        mt2.start(); 
          
        // main thread waiting for threads A and B  
        mt1.join(); 
        mt2.join(); 
          
        // count will always remain 0 after 
        // both threads will complete their execution 
        System.out.println("count: " + Shared.count); 

	}
}
	/*
	The program uses a semaphore to control access to the count variable, 
	which is a static variable within the Shared class. Shared.count is incremented 
	five times by thread A and decremented five times by thread B.To prevent these two 
	threads from accessing Shared.count at the same time, access is allowed only after 
	a permit is acquired from the controlling semaphore. After access is complete, 
	the permit is released. In this way, only one thread at a time will access Shared.count, as the output shows.
	Notice the call to sleep( ) within run( ) method inside MyThread class. 
	It is used to “prove” that accesses to Shared.count are synchronized by the semaphore. 
	In run( ), the call to sleep( ) causes the invoking thread to pause between each access to Shared.count. 
	This would normally enable the second thread to run. However, because of the semaphore, 
	the second thread must wait until the first has released the permit, 
	which happens only after all accesses by the first thread are complete. 
	Thus, Shared.count is first incremented five times by thread A and then decremented five times by thread B. 
	The increments and decrements are not intermixed at assembly code.
	Without the use of the semaphore, accesses to Shared.count by both threads would have occurred simultaneously, 
	and the increments and decrements would be intermixed.To confirm this, try commenting out the calls to acquire( )
	 and release( ). When you run the program, you will see that access to Shared.count is no longer synchronized, 
	 thus you will not always get count value 0.
	 */

