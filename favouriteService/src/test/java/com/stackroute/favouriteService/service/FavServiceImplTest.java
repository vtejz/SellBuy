package com.stackroute.favouriteService.service;

import com.stackroute.favouriteService.exception.ProductAlreadyExists;
import com.stackroute.favouriteService.model.Favourite;
import com.stackroute.favouriteService.repository.FavRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class FavServiceImplTest {
   @InjectMocks
    FavServiceImpl favService;

   @Mock
    FavRepo favRepo;

   @Test
    public void addFavShouldReturnFavAdded() throws ProductAlreadyExists {
       Favourite favourite=new Favourite();
       favourite.setUserId("123A");
       Mockito.when(favRepo.save(Mockito.any())).thenReturn(favourite);
       Favourite expected=favourite;
       Favourite actual=favService.addFav(favourite);
       assertEquals(expected,actual);
   }

//   @Test
//    public void addFavThenThrowProductAlreadyExistsIfAlreadyExits(){
//       Favourite favourite=new Favourite();
//       favourite.setUserId("123A");
//       Favourite expected=favourite;
//       Mockito.when(favRepo.findById(Mockito.anyString())).thenReturn(Optional.of(favourite));
//       assertThatThrownBy(()->favService.addFav(favourite,"123A")).isInstanceOf(ProductAlreadyExists.class);
//   }
//


   @Test
    public void viewAllFav(){
       Favourite favourite=new Favourite();
       favourite.setUserId("123");
       List<Favourite> favouriteList = Collections.singletonList(favourite);
       List<Favourite> expectedRes=favouriteList;
       Mockito.when(favRepo.findAll()).thenReturn(favouriteList);
       List<Favourite> actualResponse =favService.viewFav();
       assertEquals(expectedRes,actualResponse);
   }



    @Test
    void shouldBeAbleToDeleteUser()  {
        Favourite favourite=new Favourite();
        favourite.setUserId("12");
        Mockito.when(favRepo.findById(Mockito.anyString())).thenReturn(Optional.of(favourite));
        favService.deleteFav("23");
        Mockito.verify(favRepo,Mockito.times(1)).deleteById(Mockito.anyString());


    }

}