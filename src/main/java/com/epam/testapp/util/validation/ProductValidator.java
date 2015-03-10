package com.epam.testapp.util.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Alena_Grouk on 2/19/2015.
 */

public final class ProductValidator {
    private static final String DATE_FORMAT = "dd-MM-yyyy";
    private static final String MODEL_PATTERN = "\\w+";
    private static final String PRICE_PATTERN = "[\\d]+[.]?[\\d]+";

    private static final String PRODUCER_ERROR_MESSAGE = "*Producer is required field! ";
    private static final String MODEL_ERROR_MESSAGE = "*Model must consist of two uppercase letters and three digits! ";
    private static final String DATE_ERROR_MESSAGE = "*Wrong date format! Proper date format: ";
    private static final String COLOR_ERROR_MESSAGE = "*Please, use one of those colors: Black, White, Yellow, Green, Red, Blue, Brown, Grey, Orange, Pink ";
    private static final String PRICE_ERROR_MESSAGE = "*Please, enter proper decimal number or choose 'Not in stock' ";

    public boolean validateProduct(ProductValidationForm product) {
        boolean valid = true;
        if (product == null) {
            return false;
        }
        if (!validateProducer(product)) {
            product.setProducerErrorMessage(PRODUCER_ERROR_MESSAGE);
            valid = false;
        }
        if (!validateModel(product)) {
            product.setModelErrorMessage(MODEL_ERROR_MESSAGE);
            valid = false;
        }
        if (!validateDate(product)) {
            product.setDateErrorMessage(DATE_ERROR_MESSAGE);
            valid = false;
        }
        if (!validateColor(product)) {
            product.setColorErrorMessage(COLOR_ERROR_MESSAGE);
            valid = false;
        }
        if (!validatePrice(product)) {
            product.setPriceErrorMessage(PRICE_ERROR_MESSAGE);
            valid = false;
        }
        product.setValid(valid);
        return valid;
    }

    public boolean validateProducer(ProductValidationForm product) {
        String producer = product.getProducer();
        if (producer != null && producer.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean validateModel(ProductValidationForm product) {
        String model = product.getModel();
        if (model != null) {
            return model.matches(MODEL_PATTERN);
        } else
            return false;
    }

    public boolean validateDate(ProductValidationForm productValidationForm) {
        String date = productValidationForm.getDate();
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            format.setLenient(false);
            format.parse(date);
        } catch (ParseException exc) {
            return false;
        }
        return true;
    }

    public boolean validateColor(ProductValidationForm productValidationForm) {
        String color = productValidationForm.getColor();
        if (color != null) {
            switch (color) {
                case "Black":
                case "White":
                case "Yellow":
                case "Green":
                case "Red":
                case "Blue":
                case "Brown":
                case "Grey":
                case "Orange":
                case "Pink":
                    return true;
                default:
                    return false;
            }
        } else {
            return false;
        }
    }

    public boolean validatePrice(ProductValidationForm productValidationForm) {
        if (productValidationForm.isNotInStock()) {
            return true;
        } else {
            String price = productValidationForm.getPrice();
            return price.matches(PRICE_PATTERN);
        }
    }
}
