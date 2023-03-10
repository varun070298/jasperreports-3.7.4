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
package net.sf.jasperreports.components.barbecue;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;

/**
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: BaseBarcodeProvider.java 3032 2009-08-27 11:32:28Z teodord $
 */
public abstract class BaseBarcodeProvider implements BarcodeProvider
{

	public Barcode createBarcode(BarcodeInfo barcodeInfo)
			throws BarcodeException
	{
		Barcode barcode = createBaseBarcode(barcodeInfo);
		barcode.setDrawingText(barcodeInfo.isDrawText());
		if (barcodeInfo.getBarWidth() != null)
		{
			barcode.setBarWidth(barcodeInfo.getBarWidth().intValue());
		}
		if (barcodeInfo.getBarHeight() != null)
		{
			barcode.setBarHeight(barcodeInfo.getBarHeight().intValue());
		}
		return barcode;
	}
	
	protected abstract Barcode createBaseBarcode(BarcodeInfo barcodeInfo)
			throws BarcodeException;

}
