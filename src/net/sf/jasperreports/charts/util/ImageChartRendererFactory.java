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
package net.sf.jasperreports.charts.util;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRRenderable;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.util.JRImageLoader;
import net.sf.jasperreports.renderers.JRSimpleImageMapRenderer;

import org.jfree.chart.JFreeChart;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: ImageChartRendererFactory.java 3032 2009-08-27 11:32:28Z teodord $
 */
public class ImageChartRendererFactory implements ChartRendererFactory
{
	
	public JRRenderable getRenderer(
		JFreeChart chart, 
		ChartHyperlinkProvider chartHyperlinkProvider,
		Rectangle2D rectangle
		)
	{
		BufferedImage bi = 
			new BufferedImage(
				(int)rectangle.getWidth(),
				(int)rectangle.getHeight(),
				BufferedImage.TYPE_INT_ARGB
				);

		List areaHyperlinks = null;

		if (chartHyperlinkProvider != null && chartHyperlinkProvider.hasHyperlinks())
		{
			areaHyperlinks = ChartUtil.getImageAreaHyperlinks(chart, chartHyperlinkProvider, (Graphics2D)bi.getGraphics(), rectangle);
		}
		else
		{
			chart.draw((Graphics2D)bi.getGraphics(), rectangle);
		}

		try
		{
			return new JRSimpleImageMapRenderer(JRImageLoader.loadImageDataFromAWTImage(bi, JRRenderable.IMAGE_TYPE_PNG), areaHyperlinks);
		}
		catch (JRException e)
		{
			throw new JRRuntimeException(e);
		}
	}

}
