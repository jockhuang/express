package biz.qianyan.search.express.web;

/**
 * @author Jock
 */
public class Navbar {

    public  int PAGESIZE = 10;
    private int       allrows;
    private int       currpage;
    private int       start;
    private int       totalpage;

    /**
     * @param currpage
     */
    public Navbar(int currpage) {
        super();
        this.currpage = currpage;
    }

    /**
     * @return the allrows
     */
    public int getAllrows() {
        return allrows;
    }

    /**
     * @return the currpage
     */
    public int getCurrpage() {
        return currpage;
    }

    /**
     * @return the start
     */
    public int getStart() {
        return start;
    }

    /**
     * @return the totalpage
     */
    public int getTotalpage() {
        return totalpage;
    }

    /**
     * @param allrows
     *            the allrows to set
     */
    public void setAllrows(int allrows) {
        this.allrows = allrows;
        this.start = 0;
        this.totalpage = 0;

        totalpage = allrows / PAGESIZE + 1;
        if (allrows % PAGESIZE == 0) {
            totalpage--;
        }
        if ((currpage > 1) && (currpage > totalpage)) {
            currpage = totalpage;
            start = (totalpage - 1) * PAGESIZE;
        }
        else if (currpage <= 1) {
            currpage = 1;
            start = 0;
        }
        else {
            start = (currpage - 1) * PAGESIZE;
        }

    }

    /**
     * @param currpage
     *            the currpage to set
     */
    public void setCurrpage(int currpage) {
        this.currpage = currpage;
    }

}
