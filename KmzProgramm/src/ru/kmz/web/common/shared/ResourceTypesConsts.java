package ru.kmz.web.common.shared;

import ru.kmz.server.data.constants.ResourceTypes;

public class ResourceTypesConsts {
	public static final String ORDER = ResourceTypes.ORDER;
	public static final String PREPARE = ResourceTypes.PREPARE;
	public static final String ASSEMBLAGE = ResourceTypes.ASSEMBLAGE;
	public static final String FOLDER = ResourceTypes.FOLDER;

	public static boolean needResource(String resourceType) {
		return resourceType.equals(PREPARE);
	}

	public static boolean isFolder(String resourceType) {
		return resourceType == null || resourceType.equals(FOLDER);
	}
}
