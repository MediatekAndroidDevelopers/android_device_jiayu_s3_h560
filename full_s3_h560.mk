$(call inherit-product, $(SRC_TARGET_DIR)/product/core_64_bit.mk)

# Inherit from the common Open Source product configuration
APNS_CONF := $(LOCAL_PATH)/configs/apns-conf.xml

$(call inherit-product, $(SRC_TARGET_DIR)/product/full_base_telephony.mk)

# Inherit from hardware-specific part of the product configuration
$(call inherit-product, device/jiayu/s3_h560/device.mk)

# Release name
PRODUCT_RELEASE_NAME := s3_h560

EXTENDED_FONT_FOOTPRINT := true

PRODUCT_DEVICE := s3_h560
PRODUCT_NAME := full_s3_h560
PRODUCT_BRAND := Jiayu
PRODUCT_MODEL := S3
PRODUCT_MANUFACTURER := Jiayu
PRODUCT_RESTRICT_VENDOR_FILES := false

# Boot animation
TARGET_SCREEN_HEIGHT      := 1920
TARGET_SCREEN_WIDTH       := 1080
TARGET_BOOTANIMATION_NAME := 1080

PRODUCT_DEFAULT_LANGUAGE := en
PRODUCT_DEFAULT_REGION   := US
