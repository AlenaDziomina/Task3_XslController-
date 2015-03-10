package com.epam.testapp.util.validation;

/**
 * Created by Alena_Grouk on 2/19/2015.
 */
public class ProductValidationForm {

    private String producer = "";
    private String model = "";
    private String date = "";
    private String color = "";
    private String price = "";
    private boolean notInStock = false;

    private boolean valid = false;

    private String producerErrorMessage = "";
    private String modelErrorMessage = "";
    private String dateErrorMessage = "";
    private String colorErrorMessage = "";
    private String priceErrorMessage = "";


    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getProducerErrorMessage() {
        return producerErrorMessage;
    }

    public void setProducerErrorMessage(String producerErrorMessage) {
        this.producerErrorMessage = producerErrorMessage;
    }

    public String getModelErrorMessage() {
        return modelErrorMessage;
    }

    public void setModelErrorMessage(String modelErrorMessage) {
        this.modelErrorMessage = modelErrorMessage;
    }

    public String getDateErrorMessage() {
        return dateErrorMessage;
    }

    public void setDateErrorMessage(String dateErrorMessage) {
        this.dateErrorMessage = dateErrorMessage;
    }

    public String getColorErrorMessage() {
        return colorErrorMessage;
    }

    public void setColorErrorMessage(String colorErrorMessage) {
        this.colorErrorMessage = colorErrorMessage;
    }

    public String getPriceErrorMessage() {
        return priceErrorMessage;
    }

    public void setPriceErrorMessage(String priceErrorMessage) {
        this.priceErrorMessage = priceErrorMessage;
    }


    public boolean isNotInStock() {
        return notInStock;
    }

    public void setNotInStock(boolean notInStock) {
        this.notInStock = notInStock;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
