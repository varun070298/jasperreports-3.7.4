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
package net.sf.jasperreports.engine.export.ooxml;

import java.awt.Color;
import java.awt.font.TextAttribute;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.base.JRBasePrintText;
import net.sf.jasperreports.engine.fonts.FontFamily;
import net.sf.jasperreports.engine.fonts.FontInfo;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.util.JRColorUtil;
import net.sf.jasperreports.engine.util.JRFontUtil;
import net.sf.jasperreports.engine.util.JRStringUtil;


/**
 * @author sanda zaharia (shertage@users.sourceforge.net)
 * @version $Id: StyleCache.java 2908 2009-07-21 14:32:01Z teodord $
 */
public class DocxRunHelper extends BaseHelper
{
	/**
	 *
	 */
	private Map fontMap = null;
	private String exporterKey = null;


	/**
	 *
	 */
	public DocxRunHelper(Writer writer, Map fontMap, String exporterKey)
	{
		super(writer);
		this.fontMap = fontMap;
		this.exporterKey = exporterKey;
	}


	/**
	 *
	 */
	public void export(JRStyle style, Map attributes, String text, Locale locale)
	{
		if (text != null)
		{
			write("      <w:r>\n");
			
			exportProps(getAttributes(style), attributes, locale);
			
			StringTokenizer tkzer = new StringTokenizer(text, "\n", true);
			while(tkzer.hasMoreTokens())
			{
				String token = tkzer.nextToken();
				if ("\n".equals(token))
				{
					write("<w:br/>");
				}
				else
				{
					write("<w:t xml:space=\"preserve\">");
					write(JRStringUtil.xmlEncode(token));//FIXMEODT try something nicer for replace
					write("</w:t>\n");
				}
			}
			write("      </w:r>\n");
		}
	}

	/**
	 *
	 */
	public void exportProps(JRStyle style, Locale locale)
	{
		JRPrintText text = new JRBasePrintText(null);
		text.setStyle(style);
		Map styledTextAttributes = new HashMap(); 
		JRFontUtil.getAttributesWithoutAwtFont(styledTextAttributes, text);
		styledTextAttributes.put(TextAttribute.FOREGROUND, text.getForecolor());
		if (style.getModeValue() == null || style.getModeValue() == ModeEnum.OPAQUE)
		{
			styledTextAttributes.put(TextAttribute.BACKGROUND, style.getBackcolor());
		}

		exportProps(getAttributes(style.getStyle()), getAttributes(style), locale);
	}

	/**
	 *
	 */
	public void exportProps(Map parentAttrs,  Map attrs, Locale locale)
	{
		write("       <w:rPr>\n");

		Object value = attrs.get(TextAttribute.FAMILY);
		Object oldValue = parentAttrs.get(TextAttribute.FAMILY);
		
		if (value != null && !value.equals(oldValue))//FIXMEDOCX the text locale might be different from the report locale, resulting in different export font
		{
			String fontFamilyAttr = (String)value;
			String fontFamily = fontFamilyAttr;
			if (fontMap != null && fontMap.containsKey(fontFamilyAttr))
			{
				fontFamily = (String) fontMap.get(fontFamilyAttr);
			}
			else
			{
				FontInfo fontInfo = JRFontUtil.getFontInfo(fontFamilyAttr, locale);
				if (fontInfo != null)
				{
					//fontName found in font extensions
					FontFamily family = fontInfo.getFontFamily();
					String exportFont = family.getExportFont(exporterKey);
					if (exportFont != null)
					{
						fontFamily = exportFont;
					}
				}
			}
			write("        <w:rFonts w:ascii=\"" + fontFamily + "\" w:hAnsi=\"" + fontFamily + "\" w:eastAsia=\"" + fontFamily + "\" w:cs=\"" + fontFamily + "\" />\n");
		}
		
		value = attrs.get(TextAttribute.FOREGROUND);
		oldValue = parentAttrs.get(TextAttribute.FOREGROUND);
		
		if (value != null && !value.equals(oldValue))
		{
			write("        <w:color w:val=\"" + JRColorUtil.getColorHexa((Color)value) + "\" />\n");
		}

		value = attrs.get(TextAttribute.BACKGROUND);
		oldValue = parentAttrs.get(TextAttribute.BACKGROUND);
		
//		if (value != null && !value.equals(oldValue))
//		{
//			//FIXME: the highlight does not accept the color hexadecimal expression, but only few color names
////			writer.write("        <w:highlight w:val=\"" + JRColorUtil.getColorHexa((Color)value) + "\" />\n");
//		}

		value = attrs.get(TextAttribute.SIZE);
		oldValue = parentAttrs.get(TextAttribute.SIZE);

		if (value != null && !value.equals(oldValue))
		{
			float fontSize = ((Float)value).floatValue();
			fontSize = fontSize == 0 ? 0.5f : fontSize;// only the special EMPTY_CELL_STYLE would have font size zero
			write("        <w:sz w:val=\"" + (2 * (fontSize)) + "\" />\n");
		}
		
		value = attrs.get(TextAttribute.WEIGHT);
		oldValue = parentAttrs.get(TextAttribute.WEIGHT);

		if (value != null && !value.equals(oldValue))
		{
			write("        <w:b w:val=\"" + value.equals(TextAttribute.WEIGHT_BOLD) + "\"/>\n");
		}

		value = attrs.get(TextAttribute.POSTURE);
		oldValue = parentAttrs.get(TextAttribute.POSTURE);

		if (value != null && !value.equals(oldValue))
		{
			write("        <w:i w:val=\"" + value.equals(TextAttribute.POSTURE_OBLIQUE) + "\"/>\n");
		}


		value = attrs.get(TextAttribute.UNDERLINE);
		oldValue = parentAttrs.get(TextAttribute.UNDERLINE);

		if (
			(value == null && oldValue != null)
			|| (value != null && !value.equals(oldValue))
			)
		{
			write("        <w:u w:val=\"" + (value == null ? "none" : "single") + "\"/>\n");
		}
		
		value = attrs.get(TextAttribute.STRIKETHROUGH);
		oldValue = parentAttrs.get(TextAttribute.STRIKETHROUGH);

		if (
			(value == null && oldValue != null)
			|| (value != null && !value.equals(oldValue))
			)
		{
			write("        <w:strike w:val=\"" + (value != null) + "\"/>\n");
		}

		value = attrs.get(TextAttribute.SUPERSCRIPT);

		if (TextAttribute.SUPERSCRIPT_SUPER.equals(value))
		{
			write("        <w:vertAlign w:val=\"superscript\" />\n");
		}
		else if (TextAttribute.SUPERSCRIPT_SUB.equals(value))
		{
			write("        <w:vertAlign w:val=\"subscript\" />\n");
		}

		write("       </w:rPr>\n");
	}


	/**
	 *
	 */
	private Map getAttributes(JRStyle style)//FIXMEDOCX put this in util?
	{
		JRPrintText text = new JRBasePrintText(null);
		text.setStyle(style);
		
		Map styledTextAttributes = new HashMap(); 
		//JRFontUtil.getAttributes(styledTextAttributes, text, (Locale)null);//FIXMEDOCX getLocale());
		JRFontUtil.getAttributesWithoutAwtFont(styledTextAttributes, text);
		styledTextAttributes.put(TextAttribute.FOREGROUND, text.getForecolor());
		if (text.getModeValue() == ModeEnum.OPAQUE)
		{
			styledTextAttributes.put(TextAttribute.BACKGROUND, text.getBackcolor());
		}

		return styledTextAttributes;
	}

}

