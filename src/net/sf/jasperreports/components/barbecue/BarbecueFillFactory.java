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

import net.sf.jasperreports.engine.component.Component;
import net.sf.jasperreports.engine.component.ComponentFillFactory;
import net.sf.jasperreports.engine.component.FillComponent;
import net.sf.jasperreports.engine.fill.JRFillCloneFactory;
import net.sf.jasperreports.engine.fill.JRFillObjectFactory;

/**
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: BarbecueFillFactory.java 3032 2009-08-27 11:32:28Z teodord $
 */
public class BarbecueFillFactory implements ComponentFillFactory
{

	public FillComponent toFillComponent(Component component,
			JRFillObjectFactory factory)
	{
		BarbecueComponent barcode = (BarbecueComponent) component;
		return new BarbecueFillComponent(barcode);
	}

	public FillComponent cloneFillComponent(FillComponent component,
			JRFillCloneFactory factory)
	{
		BarbecueFillComponent fillBarcode = (BarbecueFillComponent) component;
		return new BarbecueFillComponent(fillBarcode.getBarcode());
	}

}
