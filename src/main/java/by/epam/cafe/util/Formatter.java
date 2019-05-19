package by.epam.cafe.util;

import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatter {
    private static final int FAULT = 1;
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";


    public int formatToStartIndex(int page, int countItemsOnPage) {
        if (page < 1) {
            page = 1;
        }
        if (countItemsOnPage < 1) {
            countItemsOnPage = 1;
        }
        return (page - 1) * countItemsOnPage;
    }

    public int formatToPage(String[] pageArr) {
        int page = 1;

        if (pageArr != null && pageArr[0] != null &&
                !pageArr[0].trim().isEmpty()) {

            try {
                page = Integer.parseInt(pageArr[0]);
                page = page < 1 ? -1 : page;

            } catch (NumberFormatException e) {
                page = -1;
            }
        }

        return page;
    }

    public BufferedImage formatImage(BufferedImage image,
                                     int pointX1,
                                     int pointX2,
                                     int pointY1,
                                     int pointY2,
                                     int height,
                                     int width) {

        if (image == null) {
            return null;
        }

        int originalImageSquare = image.getHeight() * image.getWidth();
        int displayedImageSquare = (height - FAULT) * (width - FAULT);
        double scale = Math.sqrt(originalImageSquare / displayedImageSquare);
        pointX1 = (int) Math.round((pointX1 * scale));
        pointX2 = (int) Math.round((pointX2 * scale));
        pointY1 = (int) Math.round((pointY1 * scale));
        pointY2 = (int) Math.round((pointY2 * scale));

        try {
            image = image.getSubimage(pointX1, pointY1, pointX2 - pointX1, pointY2 - pointY1);
        } catch (RasterFormatException e) {
            image = null;
        }

        return image;
    }

    public Date formatToDate(String stringDate, String stringTime) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);


        if (stringDate == null || stringTime == null) {
            return null;
        }

        String stringDateTime = stringDate.trim().concat(" ").concat(stringTime).trim();
        Date resultDate;

        try {
            resultDate = dateFormatter.parse(stringDateTime);

        } catch (ParseException e) {
            resultDate = null;
        }

        return resultDate;
    }


}
