package pojo;
import java.util.Date;
import java.util.TreeMap;

public class ResponceLatestUsdGbp {

    private String base;
    private TreeMap<String, Double> rates;
    private Date date;

    public ResponceLatestUsdGbp(String base, TreeMap<String, Double> rates, Date date) {
        this.base = base;
        this.rates = rates;
        this.date = date;
    }

    public ResponceLatestUsdGbp() {
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public TreeMap<String, Double> getRates() {
        return rates;
    }

    public void setRates(TreeMap<String, Double> rates) {
        this.rates = rates;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
