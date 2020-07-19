#include <jni.h>
/* Header for class TestCal */
#include "TestCal.h"
#include<stdio.h>
#include<math.h>

/*
 * Class:     TestCal
 * Method:    add
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_TestCal_add
  (JNIEnv *env, jobject obj, jint n1, jint n2){
  jint res;
  res = n1 + n2;
  return res;
  }

/*
 * Class:     TestCal
 * Method:    sub
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_TestCal_sub
  (JNIEnv *env, jobject obj, jint n1, jint n2){
  jint res;
  res = n1 - n2;
  return res;     
  
 }