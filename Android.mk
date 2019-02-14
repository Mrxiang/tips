LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := $(call all-java-files-under,src)


LOCAL_CERTIFICATE := platform
LOCAL_PRIVATE_PLATFORM_APIS := true
LOCAL_PACKAGE_NAME := Tips

LOCAL_STATIC_JAVA_LIBRARIES := android-support-v4

LOCAL_PROGUARD_ENABLED := disabled

include $(BUILD_PACKAGE)
#include $(BUILD_MULTI_PREBUILT)
# Use the following include to make our test apk.
include $(call all-makefiles-under,$(LOCAL_PATH))
