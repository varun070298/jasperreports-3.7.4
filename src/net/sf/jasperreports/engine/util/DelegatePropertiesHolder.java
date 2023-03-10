/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.jasperreports.engine.util;

import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;

/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: TextMeasurer.java 3047 2009-09-15 13:48:01Z teodord $
 */
public class DelegatePropertiesHolder implements JRPropertiesHolder
{
	private JRPropertiesHolder propertiesHolder = null;
	private JRPropertiesHolder parentProperties = null;
	
	public DelegatePropertiesHolder(
		JRPropertiesHolder propertiesHolder, 
		JRPropertiesHolder parentProperties
		) 
	{
		this.propertiesHolder = propertiesHolder;
		this.parentProperties = parentProperties;
	}
	
	public boolean hasProperties() 
	{
		return propertiesHolder == null ? false : propertiesHolder.hasProperties();
	}
	
	public JRPropertiesMap getPropertiesMap() 
	{
		return propertiesHolder == null ? null : propertiesHolder.getPropertiesMap();
	}
	
	public JRPropertiesHolder getParentProperties() 
	{
		return 
			parentProperties == null 
				? (propertiesHolder == null ? null : propertiesHolder.getParentProperties()) 
				: parentProperties;
	}
}

