package com.system.casaroto.service;

import com.system.casaroto.domain.excessoes.CredentialsException;
import com.system.casaroto.domain.excessoes.FindByIdException;
import com.system.casaroto.domain.person.Person;
import com.system.casaroto.domain.store.RegisterStoreDTO;
import com.system.casaroto.domain.store.ResponseListStoreDTO;
import com.system.casaroto.domain.store.Store;
import com.system.casaroto.repository.PersonRepository;
import com.system.casaroto.repository.StoreRepository;
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
            storeEntity.setName(data.name());
            storeEntity.setAddress(data.address());
            storeEntity.setCnpj(data.cnpj());
            storeEntity.setStatus(data.status());
            storeRepository.save(storeEntity);
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

    private void returnCNPJStore(String data) {
        var store =  storeRepository.findByCnpj(data);
        if (store != null) throw new CredentialsException("Já existe um cadastro com este CNPJ");
    }

    private Person returnPersonId(Long data) {
        return personRepository.findById(data).orElseThrow(() -> new FindByIdException("Usuario não encontrado"));
    }

    private Store returnIdStore(Long id) {
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
