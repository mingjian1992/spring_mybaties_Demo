/*
 * Copyright (c) 2013 Honyee Industry Group Co., Ltd
 * www.honyee.biz
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Honyee Industry Group Co., Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Honyee Industry Group Co., Ltd.
 */
package com.andaily.zhishifenzi.infrastructure;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Shengzhao Li
 */
public abstract class DimensionalCodeHelper {


    //Default size:  200 * 200
    public static byte[] encode(String content) {
        return encode(content, 200, 200);
    }


    public static byte[] encode(String content, int width, int height) {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix matrix;
        try {
            matrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        } catch (WriterException e) {
            throw new IllegalStateException(e);
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            MatrixToImageWriter.writeToStream(matrix, "png", os);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return os.toByteArray();
    }

}