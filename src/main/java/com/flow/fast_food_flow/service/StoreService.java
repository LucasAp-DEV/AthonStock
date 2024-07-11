package com.flow.fast_food_flow.service;

import com.flow.fast_food_flow.domain.excessoes.CredentialsException;
import com.flow.fast_food_flow.domain.excessoes.FindByIdException;
import com.flow.fast_food_flow.domain.person.Person;
import com.flow.fast_food_flow.domain.store.RegisterStoreDTO;
import com.flow.fast_food_flow.domain.store.ResponseListStoreDTO;
import com.flow.fast_food_flow.domain.store.Store;
import com.flow.fast_food_flow.repository.PersonRepository;
import com.flow.fast_food_flow.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class StoreService {

    public final StoreRepository storeRepository;

    public final PersonRepository personRepository;

    public void registerStore(RegisterStoreDTO data) {
        validatePersonId(data.personId());
        var person = returnPersonId(data.personId());
        returnCNPJStore(data.cnpj());
        validateCredentials(data);
        try {
            Store store = new Store(data.name(), data.address(), data.cnpj(),data.status(), person);
            storeRepository.save(store);
        }catch (Exception e) {throw new CredentialsException("Nao foi possivel realizar o cadastro.");}
    }

    public void updateStore(Long id, RegisterStoreDTO data) {
        validatePersonId(data.personId());
        validateCredentials(data);
        returnPersonId(data.personId());
        var storeEntity = returnIdStore(id);
        try {
            storeEntity.setName(data.name());
            storeEntity.setAddress(data.address());
            storeEntity.setCnpj(data.cnpj());
            storeEntity.setStatus(data.status());
            storeRepository.save(storeEntity);
        } catch (Exception e) {throw new CredentialsException("Nao foi possivel atualizar o Store pois ");}
    }

    public List<ResponseListStoreDTO> returnListPersonstore(Long id) {
        returnPersonId(id);
        try {
            List<Store> findByPerson = storeRepository.findByPerson_Id(id);
            List<ResponseListStoreDTO> responseDTO = new ArrayList<>();
            for (Store store : findByPerson) {
                if (store != null) {
                    ResponseListStoreDTO responseListStoreDTO = new ResponseListStoreDTO(
                            store.getName(),
                            store.getCnpj(),
                            store.getAddress(),
                            store.getPerson().getName()
                    );
                    responseDTO.add(responseListStoreDTO);
                }
            }
            return responseDTO;
        }catch (Exception e) {
            throw new FindByIdException("Erro ao buscar Stores");
        }
    }

    public void returnCNPJStore(String data) {
        var store =  storeRepository.findByCnpj(data);
        if (store != null) throw new CredentialsException("Já existe um cadastro com este CNPJ");
    }

    public Person returnPersonId(Long data) {
        return personRepository.findById(data).orElseThrow(() -> new FindByIdException("Usuario não encontrado"));
    }

    public Store returnIdStore(Long id) {
        return storeRepository.findById(id).orElseThrow(() -> new FindByIdException("Store não encontrado"));
    }

    private void validateCredentials(RegisterStoreDTO data) {
        validateField(data.name(), "Nome");
        validateField(data.cnpj(), "CNPJ");
        validateField(data.address(), "Endereço");
    }

    private void validateField(String value, String fieldName) {
        if (Objects.isNull(value) || value.isBlank()) {throw new CredentialsException("Necessario informar " + fieldName);}
    }
    private void validatePersonId(Long id) {
        if (Objects.isNull(id)) {throw new CredentialsException("Necessario informar um ID de pessoa");}
    }
}
