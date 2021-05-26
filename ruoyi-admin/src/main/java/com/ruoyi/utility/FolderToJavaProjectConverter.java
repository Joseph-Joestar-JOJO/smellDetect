package com.ruoyi.utility;

import com.ruoyi.beans.ClassBean;
import com.ruoyi.beans.PackageBean;
import com.ruoyi.parser.ClassParser;
import com.ruoyi.parser.CodeParser;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class FolderToJavaProjectConverter {
	public static Vector<ClassBean> classes = new Vector<ClassBean>();
	
	
	public static Vector<ClassBean> extractClasses(String pPath){
		Vector<ClassBean> system = new Vector<ClassBean>();
		Vector<PackageBean> packages = null;
		
		try {
			//Convert the folder in a Java Project
			packages = FolderToJavaProjectConverter.convert(pPath);	
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		//Create vector of all the classes in the system
		for(PackageBean packageBean: packages) {
			for(ClassBean classBean: packageBean.getClasses()) {
				system.add(classBean);
			}
		}
		
		FolderToJavaProjectConverter.classes = system;
		
		return system;
	}

	public static Vector<PackageBean> convert(String pPath) throws CoreException {
		File projectDirectory = new File(pPath);
		CodeParser codeParser = new CodeParser();
		Vector<PackageBean> packages = new Vector<PackageBean>();

		if(projectDirectory.isDirectory()) {
			for(File subDir: projectDirectory.listFiles()) {

				if(subDir.isDirectory()) {
					Vector<File> javaFiles = FolderToJavaProjectConverter.listJavaFiles(subDir);
					if(javaFiles.size() > 0) {
						for(File javaFile: javaFiles) {
							System.out.println(javaFile.getName());
							if(javaFile.getName().equals("JMSAppender.java")){
								continue;
							}
							//Get file Path
							String filePath = projectDirectory.toURI().relativize(javaFile.toURI()).getPath();
							filePath = filePath.substring(0, filePath.lastIndexOf('.'));
							//String filePath = javaFile.getAbsolutePath();
							CompilationUnit parsed;
							try {
								parsed = codeParser.createParser(FileUtility.readFile(javaFile.getAbsolutePath()));
								int classNum = parsed.types().size();
								for (int i = 0; i < classNum; i++) {
									TypeDeclaration typeDeclaration = (TypeDeclaration)parsed.types().get(i);

									Vector<String> imports = new Vector<String>();

									for(Object importedResource: parsed.imports())
										imports.add(importedResource.toString());

									if(! FolderToJavaProjectConverter.isAlreadyCreated(
											parsed.getPackage().getName().getFullyQualifiedName(), packages)) {

										PackageBean packageBean = new PackageBean();
										packageBean.setName(parsed.getPackage().getName().getFullyQualifiedName());

										ClassBean classBean = ClassParser.parse(typeDeclaration, packageBean.getName(), imports, filePath,parsed);
										int start = typeDeclaration.getStartPosition();
										int end = start + typeDeclaration.getLength();
										int startL = parsed.getLineNumber(start);
										int endL = parsed.getLineNumber(end);
										classBean.setStartLine(startL);
										classBean.setEndLine(endL);
										packageBean.addClass(classBean);
										packages.add(packageBean);

									} else {
										PackageBean packageBean = FolderToJavaProjectConverter.getPackageByName(
												parsed.getPackage().getName().getFullyQualifiedName(), packages);
										ClassBean classBean = ClassParser.parse(typeDeclaration, packageBean.getName(), imports, filePath,parsed);
										int start = typeDeclaration.getStartPosition();
										int end = start + typeDeclaration.getLength();
										int startL = parsed.getLineNumber(start);
										int endL = parsed.getLineNumber(end);
										classBean.setStartLine(startL);
										classBean.setEndLine(endL);
										packageBean.addClass(classBean);
									}
								}
							} catch (IOException e) {
								e.printStackTrace();
							} catch (NullPointerException e) {
								// do nothing
							} catch (IndexOutOfBoundsException e) {
								// do nothing
							}
						}
					}
				}
			}
		}

		Vector<ClassBean> classes = new Vector<ClassBean>();

		for(PackageBean pb: packages) {
			StringBuilder textualContent=new StringBuilder();
			StringBuilder processsedContent=new StringBuilder();
			for(ClassBean cb: pb.getClasses()) {
				classes.add(cb);
				textualContent.append(cb.getTextContent());
				processsedContent.append(cb.getProcessedText());
			}
			pb.setTextContent(textualContent.toString());
			pb.setProcessedText(processsedContent.toString());
		}

//		for(PackageBean packageBean: packages) {
//			for(ClassBean classBean: packageBean.getClasses()) {
//				for(MethodBean methodBean: classBean.getMethods()) {
//
//					for(MethodBean invoked: methodBean.getMethodCalls()) {
//						MethodParser.setOtherInfo(invoked, classBean, classes);
//					}
//				}
//			}
//		}


		return packages;
	}
	
	private static Vector<File> listJavaFiles(File pDirectory) {
		Vector<File> javaFiles=new Vector<File>(); 
		File[] fList = pDirectory.listFiles();

		if(fList != null) {
			for (File file : fList) {
				if (file.isFile()) {
					if(file.getName().contains(".java")) {
						javaFiles.add(file);
					}
				} else if (file.isDirectory()) {
					File directory = new File(file.getAbsolutePath());
					javaFiles.addAll(listJavaFiles(directory));
				}
			}
		}
		return javaFiles;
	}
	
	public static boolean isAlreadyCreated(String pPackageName, Vector<PackageBean> pPackages) {
		for(PackageBean pb: pPackages) {
			if(pb.getName().equals(pPackageName))
				return true;
		}
		
		return false;
	}
	
	public static PackageBean getPackageByName(String pPackageName, Vector<PackageBean> pPackages) {
		for(PackageBean pb: pPackages) {
			if(pb.getName().equals(pPackageName))
				return pb;
		}
		return null;
	}
}
