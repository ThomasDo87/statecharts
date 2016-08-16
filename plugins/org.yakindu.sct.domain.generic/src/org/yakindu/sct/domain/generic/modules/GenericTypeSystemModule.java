/**
 * Copyright (c) 2011 committers of YAKINDU and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     committers of YAKINDU - initial API and implementation
 */
package org.yakindu.sct.domain.generic.modules;

import org.eclipse.xtext.service.AbstractGenericModule;
import org.yakindu.base.base.BasePackage;
import org.yakindu.base.types.inferrer.ITypeSystemInferrer;
import org.yakindu.base.types.typesystem.GenericTypeSystem;
import org.yakindu.base.types.typesystem.ITypeSystem;
import org.yakindu.sct.domain.extension.IDomainInjectorProvider;
import org.yakindu.sct.model.stext.inferrer.STextTypeInferrer;

import com.google.inject.Binder;
import com.google.inject.name.Names;

/**
 * 
 * Default Module used for type contribution and type inference. Override
 * bindings to specify custom behavior.
 * 
 * @author andreas muelder - Initial contribution and API
 * 
 */
public class GenericTypeSystemModule extends AbstractGenericModule {

	@Override
	public void configure(Binder binder) {
		super.configure(binder);
		binder.bind(String.class).annotatedWith(Names.named(IDomainInjectorProvider.DOMAIN_ID)).toInstance(getDomainID());
		binder.bind(ITypeSystem.class).toInstance(getTypeSystem());
	}

	protected String getDomainID() {
		return BasePackage.Literals.DOMAIN_ELEMENT__DOMAIN_ID.getDefaultValueLiteral();
	}
	
	protected ITypeSystem getTypeSystem() {
		return GenericTypeSystem.getInstance();
	}

	public Class<? extends ITypeSystemInferrer> bindITypeSystemInferrer() {
		return STextTypeInferrer.class;
	}

}
