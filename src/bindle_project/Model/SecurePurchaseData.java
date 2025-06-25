package bindle_project.Model;

public class SecurePurchaseData {
    private int buyerId;
    private int sellerId;
    private int bookId;
    private String status;

    public SecurePurchaseData(int buyerId, int sellerId, int bookId, String status) {
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.bookId = bookId;
        this.status = status;
    }

    public int getBuyerId() { return buyerId; }
    public int getSellerId() { return sellerId; }
    public int getBookId() { return bookId; }
    public String getStatus() { return status; }
}
