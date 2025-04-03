/**
 * Manages cans and bottles by tracking quantities and calculating their total value.
 */
package vinnsla;

public class Floskur {

    private int dosir;  // Number of cans
    private int floskur;  // Number of bottles
    private int samtalsDosir;  // Total processed cans
    private int samtalsFloskur;  // Total processed bottles
    private int samtalsVirdi;  // Total accumulated value

    private static final int DOSA_VERD = 15;  // Value per can
    private static final int FLOSKA_VERD = 25;  // Value per bottle

    public void setDosir(int dosir) {
        this.dosir = dosir;
    }

    public void setFloskur(int floskur) {
        this.floskur = floskur;
    }

    /**
     * Calculates and returns the total value of the current cans.
     * @return the value of the cans based on the current count and unit price
     */
    public int getDosirVirdi() {
        return dosir * DOSA_VERD;
    }

    /**
     * Calculates and returns the total value of the current bottles.
     * @return the value of the bottles based on the current count and unit price
     */
    public int getFloskurVirdi() {
        return floskur * FLOSKA_VERD;
    }

    /**
     * Returns the combined value of the current cans and bottles.
     * @return the total value of the current cans and bottles
     */
    public int getSamtalsVirdi() {
        return getDosirVirdi() + getFloskurVirdi();
    }

    /**
     * Returns the total accumulated value of all processed cans.
     * @return the total value of all processed cans
     */
    public int getSamtalsDosirVirdi() {
        return samtalsDosir * DOSA_VERD;
    }

    /**
     * Returns the total accumulated value of all processed bottles.
     * @return the total value of all processed bottles
     */
    public int getSamtalsFloskurVirdi() {
        return samtalsFloskur * FLOSKA_VERD;
    }

    /**
     * Returns the total accumulated value of all processed cans and bottles.
     * @return the total value of processed cans and bottles
     */
    public int getTotalVirdi() {
        return samtalsVirdi;
    }

    /**
     * Resets the current can and bottle counts.
     */
    public void hreinsa() {
        dosir = 0;
        floskur = 0;
    }

    /**
     * Processes and accumulates current can and bottle values, then resets counts.
     */
    public void greida() {
        samtalsDosir += dosir;
        samtalsFloskur += floskur;
        samtalsVirdi += getSamtalsVirdi();
        hreinsa();
    }

    public int getSamtalsDosir() {
        return samtalsDosir;
    }

    public int getSamtalsFloskur() {
        return samtalsFloskur;
    }

}
