package com.andaily.zhishifenzi.infrastructure.generator;

import java.math.BigDecimal;

/**
 * 14-6-7 上午12:26
 *
 * @author Shengzhao Li
 */
public class AdjustmentScaler {


    private float widthScale = 1l;
    private float heightScale = 1l;

    public AdjustmentScaler(int origWidth, int origHeight, int maxWidth, int maxHeight) {
        initialAdjustScale(origWidth, origHeight, maxWidth, maxHeight);
    }

    private void initialAdjustScale(int origWidth, int origHeight, int maxWidth, int maxHeight) {
        if (origWidth > maxWidth && origHeight < maxHeight) {
            widthScale = new BigDecimal(origWidth).divide(new BigDecimal(maxWidth), 2, BigDecimal.ROUND_HALF_EVEN).floatValue();
        } else if (origWidth < maxWidth && origHeight > maxHeight) {
            this.widthScale = this.heightScale = new BigDecimal(origHeight).divide(new BigDecimal(maxHeight), 2, BigDecimal.ROUND_HALF_EVEN).floatValue();
        } else if (origWidth > maxWidth && origHeight > maxHeight) {
            BigDecimal origScale = new BigDecimal(origWidth).divide(new BigDecimal(origHeight), 2, BigDecimal.ROUND_HALF_EVEN);
            int tempMaxHeight = new BigDecimal(maxWidth).divide(origScale, 2, BigDecimal.ROUND_HALF_EVEN).intValue();

            if (tempMaxHeight <= maxHeight) {
                widthScale = new BigDecimal(origWidth).divide(new BigDecimal(maxWidth), 2, BigDecimal.ROUND_HALF_EVEN).floatValue();
                heightScale = new BigDecimal(origHeight).divide(new BigDecimal(tempMaxHeight), 2, BigDecimal.ROUND_HALF_EVEN).floatValue();
            } else {
                int tempMaxWidth = new BigDecimal(maxHeight).multiply(origScale).intValue();
                widthScale = new BigDecimal(origWidth).divide(new BigDecimal(tempMaxWidth), 2, BigDecimal.ROUND_HALF_EVEN).floatValue();
                heightScale = new BigDecimal(origHeight).divide(new BigDecimal(maxHeight), 2, BigDecimal.ROUND_HALF_EVEN).floatValue();
            }
        }
    }

    public float widthScale() {
        return widthScale;
    }

    public float heightScale() {
        return heightScale;
    }
}
