package com.flow.fast_food_flow.service;

import com.flow.fast_food_flow.domain.excessoes.CredentialsException;
import com.flow.fast_food_flow.domain.excessoes.FindByIdException;
import com.flow.fast_food_flow.domain.store.RegisterStoreDTO;
import com.flow.fast_food_flow.domain.store.Store;
import com.flow.fast_food_flow.repository.PersonRepository;
import com.flow.fast_food_flow.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class StoreService {

    public final StoreRepository storeRepository;

    public final PersonRepository personRepository;

    public void registerStore(Store data) {
        validatePersonId(data.getPerson().getId());
        returnPersonId(data.getPerson().getId());
        returnCNPJStore(data.getCnpj());
        validateCredentials(data);
        try {
            Store store = new Store(data.getName(), data.getAddress(), data.getCnpj(), data.getPerson());
            storeRepository.save(store);
        }catch (Exception e) {throw new CredentialsException("Nao foi possivel realizar o cadastro.");}
    }

    public void returnCNPJStore(String data) {
        var store =  storeRepository.findByCnpj(data);
        if (store != null) throw new CredentialsException("Já existe um cadastro com este CNPJ");
    }

    public void returnPersonId(Long data) {
        personRepository.findById(data).orElseThrow(() -> new FindByIdException("Usuario não encontrado"));
    }

    private void validateCredentials(Store data) {
        validateField(data.getName(), "Nome");
        validateField(data.getCnpj(), "CNPJ");
        validateField(data.getAddress(), "Endereço");
    }
    private void validateField(String value, String fieldName) {
        if (Objects.isNull(value) || value.isBlank()) {throw new CredentialsException("Necessario informar " + fieldName);}
    }
    private void validatePersonId(Long data) {
        if (Objects.isNull(data)) {throw new CredentialsException("Necessario informar um ID de pessoa");}
    }
}
