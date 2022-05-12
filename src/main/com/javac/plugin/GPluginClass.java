package com.javac.plugin;

import com.sun.source.tree.*;
import com.sun.source.util.*;
import com.sun.tools.javac.api.BasicJavacTask;
import com.sun.tools.javac.util.Context;
import javax.lang.model.element.TypeParameterElement;
import javax.tools.JavaCompiler;
import java.util.*;


public class GPluginClass implements Plugin {

    public static final String NAME = "GChecker";
    private static final boolean TRUE =true ;
    private static final boolean FALSE = false;

    private static NewClassTree node1;
    private static WildcardTree node2;
    private static Class<?> Fparam;
    private static Class<?> Sparam;
    private static String str;

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void init(JavacTask task, String... args) {
        Context context = ((BasicJavacTask) task).getContext();
        task.addTaskListener(new TaskListener() {
            @Override
            public void started(TaskEvent e) {
            }

            @Override
            public void finished(TaskEvent e) {
                if (e.getKind() != TaskEvent.Kind.PARSE) {
                    if(e.getTypeElement()!=null) {  
                        String s=e.getTypeElement().getSimpleName().toString();
                        List<? extends TypeParameterElement>	ele=e.getTypeElement().getTypeParameters(); 
                    }
                        return;
                }

              e.getCompilationUnit()
                    .accept(new TreeScanner<Void, Void>() {

                        public Void visitNewClass(NewClassTree node,
                                                  Void p) {
                          if (node.isAnnotationPresent(GCheck.class)){
						  setNew(node);}
                            return super.visitNewClass(node,p);
                                                 } }, null);
                        e.getCompilationUnit()
                        .accept(new TreeScanner<Void, Void>() {

                            public Void visitParameterizedType(ParameterizedTypeTree node,
                                                               Void p) {
                                Tree type=node.getType();
                                List<? extends Tree> ptype=node.getTypeArguments();
                                          return super.visitParameterizedType(node,p);
                            }
                        }, null);
                         e.getCompilationUnit()
                        .accept(new TreeScanner<Void, Void>() {

                            public Void visitWildcard(WildcardTree node,
                                                      Void p) {
								if (node.isAnnotationPresent(GCheck.class)){					  
                                setWild(node);}
                                try{
                                    validate();
                                }catch(Exception e){
                                  
                                    try {
                                        throw new GenericArgumentException(e.getMessage(),"Unsound inference",e.getLocalizedMessage());
                                    } catch (GenericArgumentException genericArgumentException) {
                                        genericArgumentException.printStackTrace();
                                    }


                                }

                                    return super.visitWildcard(node,p);
            }
        }, null);
            } 
        }); 
    } 
    private void setNew(NewClassTree node){
     this.node1=node;
        List<? extends ExpressionTree> actualParams = node1.getArguments();
        Integer i= Integer.parseInt(actualParams.toString());
        Fparam=i.getClass();
    }
	
    private void setWild(WildcardTree node){
        this.node2=node;
        Tree bound=node2.getBound();
        String s=node2.getBound().toString();
     
       if(s.equalsIgnoreCase("Integer")){
          Integer i=0;
          Sparam=i.getClass();
       }
       else{
           Sparam=s.getClass();}
       str=Sparam.getSimpleName();
       }
    private void validate()
    {
        if (Fparam != Sparam) {
            throw new IllegalArgumentException(
                    "Constructor argument is of type is not compatible with Class argument type"
            );
        }

            }
    
}
