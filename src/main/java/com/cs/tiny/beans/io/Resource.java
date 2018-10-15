package com.cs.tiny.beans.io;

import java.io.InputStream;

/**
 * @author benjaminChan
 * @date 2018/10/15 0015 上午 10:56
 */
public interface Resource {

    InputStream getInputStream() throws Exception;
}
