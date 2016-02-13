$(call inherit-product, device/jiayu/s3_h560/device_s3_h560.mk)

# Inherit some common CM stuff.
$(call inherit-product, vendor/cm/config/common_full_phone.mk)

## Device identifier. This must come after all inclusions
PRODUCT_DEVICE := s3_h560
PRODUCT_NAME := cm_s3_h560
PRODUCT_BRAND := Jiayu
PRODUCT_MODEL := S3
PRODUCT_MANUFACTURER := Jiayu
