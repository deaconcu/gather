package com.jike.mobile.gather.common.thrift;
/**
 * Autogenerated by Thrift Compiler (0.8.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */

import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum OperationType implements org.apache.thrift.TEnum {
  PUT(1),
  MPUT(2),
  READ(3),
  MREAD(4),
  DELETE(5),
  MDELETE(6),
  PUTLIST(7),
  MPUTLIST(8),
  GETLIST(9),
  MGETLIST(10),
  DELETELIST(11),
  MDELETELIST(12);

  private final int value;

  private OperationType(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static OperationType findByValue(int value) { 
    switch (value) {
      case 1:
        return PUT;
      case 2:
        return MPUT;
      case 3:
        return READ;
      case 4:
        return MREAD;
      case 5:
        return DELETE;
      case 6:
        return MDELETE;
      case 7:
        return PUTLIST;
      case 8:
        return MPUTLIST;
      case 9:
        return GETLIST;
      case 10:
        return MGETLIST;
      case 11:
        return DELETELIST;
      case 12:
        return MDELETELIST;
      default:
        return null;
    }
  }
}
