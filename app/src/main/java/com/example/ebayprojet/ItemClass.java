package com.example.ebayprojet;

public class ItemClass {
    private String mImageUrl = "0";
    private String mtitle = "0";
    private String mshippingPrice = "0";
    private String mcondition = "0";
    private String mprice = "0";
    private String mtopRated;
    private String mitemUrl;
    private String mitemId;
    private String mhandlingTime;
    private String moneDayShippingAvailable;
    private String mshippingType;
    private String mshippingFrom;
    private String mshipToLocation;
    private String mexpeditedShipping;

    public ItemClass(String imageUrl, String title, String shippingPrice, String condition, String price, String topRated,
                     String itemUrl, String itemId, String handlingTime, String oneDayShippingAvailable, String shippingType,
                     String shippingFrom, String shipToLocation, String expeditedShipping){
        this.mImageUrl = imageUrl;
        this.mtitle = title;
        this.mshippingPrice = shippingPrice;
        this.mcondition = condition;
        this.mprice = price;
        this.mtopRated = topRated;
        this.mitemUrl = itemUrl;
        this.mitemId = itemId;
        this.mhandlingTime = handlingTime;
        this.moneDayShippingAvailable = oneDayShippingAvailable;
        this.mshippingType = shippingType;
        this.mshippingFrom = shippingFrom;
        this.mshipToLocation = shipToLocation;
        this.mexpeditedShipping = expeditedShipping;
    }

    public String getImageUrl(){
        return this.mImageUrl;
    }

    public String gettitle(){
        return this.mtitle;
    }

    public String getshippingPrice(){
        return "$"+this.mshippingPrice;
    }

    public String getcondition(){
        return this.mcondition;
    }

    public String getprice(){
        return "$"+this.mprice;
    }

    public String getMtopRated(){
        return this.mtopRated;
    }

    public String getitemUrl(){return this.mitemUrl;}

    public String getmitemId(){return this.mitemId;}

    public String gethandlingTime(){return this.mhandlingTime;};
    public String getoneDayShippingAvailable(){return this.moneDayShippingAvailable;};
    public String getshippingType(){return this.mshippingType;};
    public String getshippingFrom(){return this.mshippingFrom;};
    public String getshipToLocation(){return this.mshipToLocation;};
    public String getexpeditedShipping(){return this.mexpeditedShipping;};
}
