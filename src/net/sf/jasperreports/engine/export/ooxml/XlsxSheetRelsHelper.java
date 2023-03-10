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
 *(at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.jasperreports.engine.export.ooxml;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: OoxmlZip.java 2908 2009-07-21 14:32:01Z teodord $
 */
public class XlsxSheetRelsHelper extends BaseHelper
{
	private Map linkCache = new HashMap();

	/**
	 * 
	 */
	public XlsxSheetRelsHelper(Writer writer)
	{
		super(writer);
	}

	/**
	 * 
	 */
	public void exportHeader(int index)
	{
		write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
		write("<Relationships xmlns=\"http://schemas.openxmlformats.org/package/2006/relationships\">\n");
		write(" <Relationship Id=\"rIdDr" + index + "\" Type=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships/drawing\" Target=\"../drawings/drawing" + index + ".xml\"/>\n");
	}
	
	/**
	 *
	 */
	public int getHyperlink(String href)
	{
		Integer linkIndex = (Integer)linkCache.get(href);
		if (linkIndex == null)
		{
			linkIndex = Integer.valueOf(linkCache.size());
			exportHyperlink(linkIndex, href);
			linkCache.put(href, linkIndex);
		}
		return linkIndex.intValue();
	}

	/**
	 * 
	 */
	private void exportHyperlink(int index, String href)
	{
		write(" <Relationship Id=\"rIdLnk" 
			+ index + "\" Type=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships/hyperlink\" Target=\"" 
			+ href + "\" TargetMode=\"External\"/>\n");
	}
	
	/**
	 * 
	 */
	public void exportFooter()
	{
		write("</Relationships>\n");
	}
	
}
