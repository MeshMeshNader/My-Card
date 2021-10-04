package com.demo.mycard.DataModels;

public class OrderDataModel {
    String orderKey;
    Integer orderNumber;
    String userID;
    String userName;
    String userCreditNumber;
    String userCreditDate;
    String userCreditID;
    String orderDate;
    String orderType;
    String orderURI;
    String orderFont;
    String orderColor;
    String orderImage;
    String orderWidth;
    String orderHeight;
    String orderText;

    public OrderDataModel() {
    }

    public OrderDataModel(String orderKey, Integer orderNumber, String userID, String userName, String userCreditNumber, String userCreditDate, String userCreditID, String orderDate, String orderType, String orderURI, String orderFont, String orderColor, String orderImage, String orderWidth, String orderHeight, String orderText) {
        this.orderKey = orderKey;
        this.orderNumber = orderNumber;
        this.userID = userID;
        this.userName = userName;
        this.userCreditNumber = userCreditNumber;
        this.userCreditDate = userCreditDate;
        this.userCreditID = userCreditID;
        this.orderDate = orderDate;
        this.orderType = orderType;
        this.orderURI = orderURI;
        this.orderFont = orderFont;
        this.orderColor = orderColor;
        this.orderImage = orderImage;
        this.orderWidth = orderWidth;
        this.orderHeight = orderHeight;
        this.orderText = orderText;
    }

    public String getUserCreditNumber() {
        return userCreditNumber;
    }

    public void setUserCreditNumber(String userCreditNumber) {
        this.userCreditNumber = userCreditNumber;
    }

    public String getUserCreditDate() {
        return userCreditDate;
    }

    public void setUserCreditDate(String userCreditDate) {
        this.userCreditDate = userCreditDate;
    }

    public String getUserCreditID() {
        return userCreditID;
    }

    public void setUserCreditID(String userCreditID) {
        this.userCreditID = userCreditID;
    }

    public String getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderURI() {
        return orderURI;
    }

    public void setOrderURI(String orderURI) {
        this.orderURI = orderURI;
    }

    public String getOrderFont() {
        return orderFont;
    }

    public void setOrderFont(String orderFont) {
        this.orderFont = orderFont;
    }

    public String getOrderColor() {
        return orderColor;
    }

    public void setOrderColor(String orderColor) {
        this.orderColor = orderColor;
    }

    public String getOrderImage() {
        return orderImage;
    }

    public void setOrderImage(String orderImage) {
        this.orderImage = orderImage;
    }

    public String getOrderWidth() {
        return orderWidth;
    }

    public void setOrderWidth(String orderWidth) {
        this.orderWidth = orderWidth;
    }

    public String getOrderHeight() {
        return orderHeight;
    }

    public void setOrderHeight(String orderHeight) {
        this.orderHeight = orderHeight;
    }

    public String getOrderText() {
        return orderText;
    }

    public void setOrderText(String orderText) {
        this.orderText = orderText;
    }
}
