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


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: StyleBuilder.java 2908 2009-07-21 14:32:01Z teodord $
 */
public class XlsxWorkbookHelper extends BaseHelper
{
	/**
	 * 
	 */
	public XlsxWorkbookHelper(Writer writer)
	{
		super(writer);
	}

	/**
	 *
	 */
	public void exportHeader()
	{
		write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		write("<workbook\n");
		write(" xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\"\n");
		write(" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\">\n");
		write("<workbookPr filterPrivacy=\"1\"/>\n");
		write("<sheets>\n");
	}
	

	/**
	 * 
	 */
	public void exportSheet(int index)
	{
		write("  <sheet name=\"Sheet" + index + "\" sheetId=\"" + index + "\" r:id=\"rId" + index + "\"/>\n");
	}
	

	/**
	 *
	 */
	public void exportFooter()
	{
		write("</sheets>\n");
		write("</workbook>\n");
	}
}
