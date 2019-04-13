/*
 * Copyright (c) 2016, ZCJ All Rights Reserved.
 * Project Name: zcj_android-V0.21
 */
package com.example.module_library.entity;
import java.io.Serializable;

/**
 * Created by fengyongge on 2016/5/24
 */
public class PhotoEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private String originalPath;
	private boolean isChecked;

	public PhotoEntity(String originalPath) {
		super();
		this.originalPath = originalPath;
	}



	public PhotoEntity() {
	}

	public String getOriginalPath() {
		return originalPath;
	}

	public void setOriginalPath(String originalPath) {
		this.originalPath = originalPath;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

}

