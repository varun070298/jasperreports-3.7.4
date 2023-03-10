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

/*
 * Contributors:
 * Greg Hilton 
 */

package net.sf.jasperreports.engine.export;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JExcelApiExporterNature.java 3033 2009-08-27 11:46:22Z teodord $
 */
public class JExcelApiExporterNature extends JRXlsAbstractExporterNature
{

	/**
	 * 
	 */
	protected JExcelApiExporterNature(ExporterFilter filter, boolean isIgnoreGraphics)
	{
		super(filter, isIgnoreGraphics);
	}
	
	/**
	 * 
	 */
	protected JExcelApiExporterNature(ExporterFilter filter, boolean isIgnoreGraphics, boolean isIgnorePageMargins)
	{
		super(filter, isIgnoreGraphics, isIgnorePageMargins);
	}
	
}
