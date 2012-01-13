package biz.qianyan.search.express.web;

public class TestNavbar {

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Navbar page = new Navbar(1);
        page.PAGESIZE = 20;
        page.setAllrows(2000);
        System.out.println(page.getStart());
        System.out.println(Math.min(2000, page.getStart() + page.PAGESIZE));
    }

}
