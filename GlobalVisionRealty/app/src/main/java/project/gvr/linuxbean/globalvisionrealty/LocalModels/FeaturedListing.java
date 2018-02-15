package project.gvr.linuxbean.globalvisionrealty.LocalModels;

/**
 * Created by user on 2/14/2018.
 */

public class FeaturedListing {

    String NoOfPhotos;
    String  rateIt;
    String  Address;
    String  mlsCode;
    String  price;
    String  beds;
    String  baths;

    public FeaturedListing(String noOfPhotos, String rateIt, String address, String mlsCode, String price, String beds, String baths) {
        NoOfPhotos = noOfPhotos;
        this.rateIt = rateIt;
        Address = address;
        this.mlsCode = mlsCode;
        this.price = price;
        this.beds = beds;
        this.baths = baths;
    }

    public String getNoOfPhotos() {
        return NoOfPhotos;
    }

    public void setNoOfPhotos(String noOfPhotos) {
        NoOfPhotos = noOfPhotos;
    }

    public String getRateIt() {
        return rateIt;
    }

    public void setRateIt(String rateIt) {
        this.rateIt = rateIt;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getMlsCode() {
        return mlsCode;
    }

    public void setMlsCode(String mlsCode) {
        this.mlsCode = mlsCode;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBeds() {
        return beds;
    }

    public void setBeds(String beds) {
        this.beds = beds;
    }

    public String getBaths() {
        return baths;
    }

    public void setBaths(String baths) {
        this.baths = baths;
    }
}
