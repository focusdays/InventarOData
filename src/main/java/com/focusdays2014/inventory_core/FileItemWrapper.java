package com.focusdays2014.inventory_core;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;

public class FileItemWrapper {

	private FileItem file;
	private String fileName;

	public FileItemWrapper(FileItem file) {
		this.file = file;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FileItemWrapper) {
			FileItemWrapper obj2 = (FileItemWrapper)obj;
			return 
					this.file.getFieldName().equals(obj2.file.getFieldName()) &&
					this.getFileName().equals(obj2.getFileName()) &&
					this.file.getContentType().equals(obj2.file.getContentType()) &&
					this.file.getSize() == obj2.file.getSize();
		} else {
			return false;
		}
	}

	/**
		String fieldName = fi.getFieldName();
		String fileName = fi.getName();
		String contentType = fi.getContentType();
		boolean isInMemory = fi.isInMemory();
		long sizeInBytes = fi.getSize();

	 */
	@Override
	public int hashCode() {
		int result = HashCodeUtil.SEED;
		result = HashCodeUtil.hash(result, this.file.getFieldName());
		result = HashCodeUtil.hash(result, this.getFileName());
		result = HashCodeUtil.hash(result, this.file.getContentType());
		result = HashCodeUtil.hash(result, this.file.getSize());
		return result;
	}
	
	public String getFileName() {
		if (this.fileName == null) {
			String fullName = this.file.getName();
			fullName = fullName.replace('\\', '/');
			int lastIndex = fullName.lastIndexOf('/');
			if (lastIndex >= 0) {
				this.fileName = fullName.substring(lastIndex);
			} else {
				this.fileName = fullName;
			}
		}
		return this.fileName;
	}
	public String getExtension() {
		return FilenameUtils.getExtension(this.file.getName());
	}

}
