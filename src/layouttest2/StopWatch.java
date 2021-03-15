/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package layouttest2;

/**
 *
 * @author Michael
 */

// The StopWatch class below is used to record times between events.

public class StopWatch {    

    private long startTime = 0;
    private long stopTime = 0;
    private boolean running = false;

    public void start() {
        this.startTime = System.currentTimeMillis();
        this.running = true;
    }

    public void stop() {
        this.stopTime = System.currentTimeMillis();
        this.running = false;
    }

    // Returns elaspsed time in milliseconds.
    
    public double getElapsedTime() {
        double elapsed;
        if (running) {            
            elapsed = (System.currentTimeMillis() - startTime);
        }
        else {
            elapsed = (stopTime - startTime);
        }        
        return elapsed;
    }

    // Returns elaspsed time in seconds.
    
    public double getElapsedTimeSecs() {
        double elapsed;
        if (running) {
            elapsed = ((System.currentTimeMillis() - startTime) / 1000);
        }
        else {
            elapsed = ((stopTime - startTime) / 1000);
        }
        return elapsed;
    }
}