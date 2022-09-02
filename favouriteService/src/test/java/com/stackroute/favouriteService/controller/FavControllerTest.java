package com.stackroute.favouriteService.controller;

import com.stackroute.favouriteService.exception.ProductAlreadyExists;
import com.stackroute.favouriteService.model.Favourite;
import com.stackroute.favouriteService.service.FavServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class FavControllerTest {
@Mock
    FavServiceImpl favService;
@Mock
    Favourite favourite;
@InjectMocks
    FavController favController;

@Test
    public void shouldViewFavById() throws ProductAlreadyExists {
    favourite.setUserId("12");
    Mockito.when(favService.addFav(favourite)).thenReturn(favourite);
    ResponseEntity<?> expected =new ResponseEntity<>(favourite, HttpStatus.CREATED);
    ResponseEntity<?> actual = favController.saveFav(favourite);
    Assertions.assertEquals(expected,actual);
}

@Test
    public void viewAllFav(){
    favourite.setUserId("a123");
    List<Favourite> favouriteList=new ArrayList<>();
    favouriteList.add(favourite);
    Mockito.when(favService.viewFav()).thenReturn(favouriteList);
    ResponseEntity<?> expected= new ResponseEntity<>(favouriteList,HttpStatus.OK);
    ResponseEntity<?> actual=favController.getFav();
    Assertions.assertEquals(expected,actual);
}

@Test
    public void deleteFav(){
    favourite.setUserId("123");
    favController.deleteProduct("123");
    Mockito.verify(favService,Mockito.times(1)).deleteFav(Mockito.anyString());
}
}