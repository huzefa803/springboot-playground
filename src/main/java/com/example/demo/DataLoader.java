package com.example.demo;

import com.example.demo.address.repository.CityRepository;
import com.example.demo.address.repository.CountryRepository;
import com.example.demo.address.repository.StateRepository;
import com.example.demo.address.repository.models.City;
import com.example.demo.address.repository.models.Country;
import com.example.demo.address.repository.models.State;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class DataLoader implements ApplicationRunner {
    StateRepository stateRepository;
    CountryRepository countryRepository;
    CityRepository cityRepository;

    public void initializeDB(StateRepository stateRepository, CountryRepository countryRepository, CityRepository cityRepository){
        try {
            String countryFile = "/Users/huzefa/Desktop/countries.csv";
            FileReader filereader = new FileReader(countryFile);
            CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
            String[] nextRecord;
            ArrayList<Country> countries = new ArrayList<>();
            while ((nextRecord = csvReader.readNext()) != null) {
                countries.add(new Country(Long.parseLong(nextRecord[0]), nextRecord[1], nextRecord[3], nextRecord[2]));
            }
            countryRepository.saveAll(countries);
            countries.clear();
            csvReader.close();
            filereader.close();

            String stateFile = "/Users/huzefa/Desktop/states.csv";
            filereader = new FileReader(stateFile);
            csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
            ArrayList<State> states = new ArrayList<>();
            while ((nextRecord = csvReader.readNext()) != null) {
                System.out.println(nextRecord[1]);
                Optional<Country> country = countryRepository.findById(Long.parseLong(nextRecord[2]));
                if (country.isPresent()){
                    states.add(new State(Long.parseLong(nextRecord[0]), nextRecord[1], country.get()));
                }else{
                    System.out.println("State not saved " + nextRecord[1] + " Country absent " + nextRecord[4]);
                }
            }
            stateRepository.saveAll(states);
            states.clear();
            csvReader.close();
            filereader.close();


            String cityFile = "/Users/huzefa/Desktop/cities.csv";
            filereader = new FileReader(cityFile);
            csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
            ArrayList<City> cities = new ArrayList<>();
            while ((nextRecord = csvReader.readNext()) != null) {
                Optional<Country> country = countryRepository.findById(Long.parseLong(nextRecord[5]));
                Optional<State> state = stateRepository.findById(Long.parseLong(nextRecord[2]));
                if (country.isPresent()){
                    if(state.isPresent()){
                        cities.add(new City(Long.parseLong(nextRecord[0]), nextRecord[1], country.get(), state.get()));
                    }else{
                        cities.add(new City(Long.parseLong(nextRecord[0]), nextRecord[1], country.get()));
                    }
                }else{
                    System.out.println("Could not find country. City not saved.");
                }
            }
            cityRepository.saveAll(cities);
            cities.clear();
            csvReader.close();
            filereader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Autowired
    public DataLoader(CountryRepository countryRepository, StateRepository stateRepository, CityRepository cityRepository){
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        initializeDB(stateRepository, countryRepository, cityRepository);
    }
}
