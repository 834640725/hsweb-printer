/*
 *  Copyright (c) 2015.  meicanyun.com Corporation Limited.
 *  All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  meicanyun Company. ("Confidential Information").  You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with meicanyun.com.
 */

package org.hsweb.printer.fx.components;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.hsweb.printer.fx.PropertyController;
import org.hsweb.printer.utils.printable.templateptint.TemplatePrintConstants;
import org.hsweb.printer.utils.printable.templateptint.dtos.TemplateComponentDTO;
import org.hsweb.printer.utils.printable.templateptint.dtos.TextComponentDTO;
import org.hsweb.printer.utils.printable.templateptint.dtos.VariableComponentDTO;

/**
 * Created by xiong on 2017-07-08.
 */
public class BasicComponent  extends Text implements Component{

    private TemplateComponentDTO baseComponentDTO;
    private ComponentEvent componentEvent;

    public BasicComponent(TemplateComponentDTO baseComponentDTO, PropertyController propertyController) {

        this.componentEvent= new ComponentEvent(this,baseComponentDTO,propertyController);

        changeProperty(baseComponentDTO);

        propertyController.property(this,baseComponentDTO);

    }

    @Override
    public TemplateComponentDTO getComponent() {
        return baseComponentDTO;
    }
    @Override
    public void changeProperty(TemplateComponentDTO baseComponentDTO){
        this.baseComponentDTO=baseComponentDTO;

        this.componentEvent.changeTemplateComponent(baseComponentDTO);

        this.setText(baseComponentDTO.getContext());
        this.setX(baseComponentDTO.getX());
        this.setY(baseComponentDTO.getY());
        this.prefHeight(baseComponentDTO.getHeight());
        this.prefWidth(baseComponentDTO.getWidth());
        this.setWrappingWidth(baseComponentDTO.getWidth());
        if(TemplatePrintConstants.TEXT.equals(baseComponentDTO.getType())){
            this.changeTextProperty((TextComponentDTO)baseComponentDTO);
        }else if(TemplatePrintConstants.VARIABLE.equals(baseComponentDTO.getType())){
            this.changeVariableProperty((VariableComponentDTO)baseComponentDTO);
        }
    }

    @Override
    public Node getThisNode() {
        return this;
    }


    private void changeTextProperty(TextComponentDTO baseComponentDTO) {
        Font font =  Font.font(baseComponentDTO.getFontName(),baseComponentDTO.getFontPosture(),baseComponentDTO.getFontSize());
        this.setFont(font);
        this.setTextAlignment(TextAlignment.LEFT);
        if(baseComponentDTO.getAlign()==1){
            this.setTextAlignment(TextAlignment.CENTER);
        }else if(baseComponentDTO.getAlign()==2){
            this.setTextAlignment(TextAlignment.RIGHT);
        }
        this.setFill(Color.valueOf(baseComponentDTO.getColor()));

    }
    private void changeVariableProperty(VariableComponentDTO baseComponentDTO) {
        this.changeTextProperty(baseComponentDTO);
    }




}