/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tgol.entity.service.contract;

import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.contract.ContractImpl;
import org.opens.tgol.entity.product.Product;
import org.opens.tgol.entity.product.ProductImpl;
import org.opens.tgol.entity.option.Option;
import org.opens.tgol.entity.option.OptionElement;
import org.opens.tgol.entity.option.OptionElementImpl;
import org.opens.tgol.entity.option.OptionImpl;
import org.opens.tgol.entity.product.Scope;
import org.opens.tgol.entity.product.ScopeEnum;
import org.opens.tgol.entity.product.TgsiScopeImpl;
import org.opens.tgol.entity.service.user.UserDataService;
import org.opens.tgol.entity.user.User;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;
import org.opens.tanaguru.sdk.entity.factory.GenericFactory;

/**
 *
 * @author jkowalczyk
 */
public class ContractDataServiceMock implements ContractDataService {

    Map<Long, Contract> contractMap = new HashMap<Long, Contract>();

    public void setUserDataService(UserDataService userDataService) {
        User user = userDataService.read(Long.valueOf(1));
        initialiseContractMap(user);
    }

    public ContractDataServiceMock(){}

    private void initialiseContractMap(User user) {
        Product product = new ProductImpl();
        product.setCode("DOMAIN");
        product.setId(Long.valueOf(1));
        Scope domainScope = new TgsiScopeImpl();
        domainScope.setCode(ScopeEnum.DOMAIN);
        product.setScope(domainScope);
        addContract(Long.valueOf(1), "Test Contract 1", "http://www.test1.com", null, null, false, user, product);
        addContract(Long.valueOf(2), "Test Contract 2", "http://www.test2.com", null, null, false, user, product);
        addContract(Long.valueOf(3), "Test Contract 3", "http://www.test3.com", "FORBIDDEN_REFERENTIAL", "Seo", true, user, product);
        addContract(Long.valueOf(4), "Test Contract 4", "http://www.test4.com", "FORBIDDEN_REFERENTIAL", "Seo;AW21", true, user, product);
        addContract(Long.valueOf(5), "Test Contract 5", "http://www.test5.com", "MAX_DOCUMENTS", "2", true, user, product);
        addContract(Long.valueOf(6), "Test Contract 6", "http://www.test6.com", "MAX_DURATION", "20", true, user, product);
        addContract(Long.valueOf(7), "Test Contract 7", "http://www.test7.com", "DEPTH", "5", true, user, product);
        addContract(Long.valueOf(8), "Test Contract 8", "http://www.test8.com", "EXCLUSION_REGEXP", "test_expression", true, user, product);
        Contract contract = addContract(Long.valueOf(10), "Test Contract 10", "http://www.test10.com", "AUTHORIZED_REFERENTIAL", "AW21", false, user, product);
        addOptionToContract(contract,"DEFAULT_LEVEL", "Seo;Bz", false);
        contract = addContract(Long.valueOf(11), "Test Contract 11", "http://www.test11.com", "AUTHORIZED_REFERENTIAL", "Seo", false, user, product);
        addOptionToContract(contract,"DEFAULT_LEVEL", "Seo;Ar", false);
        contract = addContract(Long.valueOf(12), "Test Contract 12", "http://www.test12.com", "AUTHORIZED_REFERENTIAL", "Seo;AW21", false, user, product);
        addOptionToContract(contract,"DEFAULT_LEVEL", "AW21;Or", false);
        product = new ProductImpl();
        product.setCode("GROUPOFPAGES");
        product.setId(Long.valueOf(2));
        domainScope = new TgsiScopeImpl();
        domainScope.setCode(ScopeEnum.GROUPOFPAGES);
        product.setScope(domainScope);
        addContract(Long.valueOf(9), "Test Contract 9", "http://www.test9.com", null, null, false, user, product);
    }

    private Contract addContract(
            Long id,
            String label,
            String url,
            String optionElementCode,
            String optionValue,
            boolean isOptionRestriction,
            User user,
            Product product) {
        Contract contract = new ContractImpl();
        contract.setId(id);
        contract.setLabel(label);
        contract.setUrl(url);
        contract.setUser(user);
        contract.setProduct(product);
        if (optionValue != null && optionElementCode != null) {
            OptionElement optionElement = new OptionElementImpl();
            optionElement.setIsRestriction(isOptionRestriction);
            Option option = new OptionImpl();
            optionElement.setCode(optionElementCode);
            option.setOptionElement(optionElement);
            option.setValue(optionValue);
            contract.addOption(option);
        }
        contractMap.put(id, contract);
        return contract;
    }

    private void addOptionToContract(
            Contract contract,
            String optionElementCode,
            String optionValue,
            boolean isOptionRestriction) {
        if (optionValue != null && optionElementCode != null) {
            OptionElement optionElement = new OptionElementImpl();
            optionElement.setIsRestriction(isOptionRestriction);
            Option option = new OptionImpl();
            optionElement.setCode(optionElementCode);
            option.setOptionElement(optionElement);
            option.setValue(optionValue);
            contract.addOption(option);
        }
    }
    
    @Override
    public Collection<Contract> getAllContractsByUser(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<Contract> getAllContractsByProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Contract create() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void create(Contract e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Contract e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long k) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Set<Contract> set) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<? extends Contract> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Contract read(Long k) {
        return contractMap.get(k);
    }

    @Override
    public Contract saveOrUpdate(Contract e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Contract> saveOrUpdate(Set<Contract> set) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityDao(GenericDAO<Contract, Long> gdao) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityFactory(GenericFactory<Contract> gf) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Contract update(Contract e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
