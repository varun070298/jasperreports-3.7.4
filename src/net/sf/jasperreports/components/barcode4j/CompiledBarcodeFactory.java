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
package net.sf.jasperreports.components.barcode4j;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.base.JRBaseObjectFactory;

/**
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: CompiledBarcodeFactory.java 3032 2009-08-27 11:32:28Z teodord $
 */
public class CompiledBarcodeFactory extends UniformBarcodeVisitor
{

	private final JRBaseObjectFactory baseFactory;
	
	private BarcodeComponent compiledComponent;

	public CompiledBarcodeFactory(JRBaseObjectFactory baseFactory)
	{
		this.baseFactory = baseFactory;
	}

	public BarcodeComponent toCompiledComponent(BarcodeComponent barcode)
	{
		barcode.receive(this);
		return compiledComponent;
	}

	protected void createCompiledBarcode(BarcodeComponent barcode)
	{
		compiledComponent = barcode.cloneObject();
		
		JRExpression compiledCodeExpression = baseFactory.getExpression(
				barcode.getCodeExpression());
		compiledComponent.setCodeExpression(compiledCodeExpression);
		
		JRExpression compiledPatternExpression = baseFactory.getExpression(
				barcode.getPatternExpression());
		compiledComponent.setPatternExpression(compiledPatternExpression);
	}

	protected void visitBarcode(BarcodeComponent barcode)
	{
		createCompiledBarcode(barcode);
	}
	
}
