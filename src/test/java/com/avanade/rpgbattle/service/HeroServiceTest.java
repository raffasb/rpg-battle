package com.avanade.rpgbattle.service;

import com.avanade.rpgbattle.model.Hero;
import com.avanade.rpgbattle.repository.IHeroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class HeroServiceTest {

    @InjectMocks
    private HeroService service;

    @Mock
    private IHeroRepository repository;

    List<Hero> itemsList;

    @BeforeEach
    void setUp( ) {
        MockitoAnnotations.openMocks( this );

        itemsList = new ArrayList<>( );

        Hero item1 = new Hero(1L, "Item 1", "Unit Test 1", 40, 30, 20, 10, 2, 6, LocalDateTime.now( ), false);
        Hero item2 = new Hero(2L, "Item 2", "Unit Test 2", 80, 60, 40, 20, 2, 6, LocalDateTime.now( ), false);
        Hero item3 = new Hero(3L, "Item 3", "Unit Test 3", 90, 70, 50, 30, 2, 6, LocalDateTime.now( ), true);

        itemsList.add( item1 );
        itemsList.add( item2 );
        itemsList.add( item3 );
    }

    @Test
    void create() {

        Hero item = new Hero(1L, "Item 1", "Unit Test - Default", 40, 30, 20, 10, 2, 6, LocalDateTime.now( ), true);

        Hero expected = new Hero(1L, "Item 1", "Unit Test - Expected", 40, 30, 20, 10, 2, 6, LocalDateTime.now( ), false);

        when( repository.save( item ) ).thenReturn( item );
        Hero response = service.create( item );

        Assertions.assertEquals( expected.getIsSystemGenerated(), response.getIsSystemGenerated() );
        verify( repository, times( 1 ) ).save( any( ) );
    }

    @Test
    void findAll() {
        when( repository.findAll( ) ).thenReturn( itemsList );
        List< Hero > items = service.findAll( );
        Assertions.assertEquals( items, itemsList );
        verify( repository, times( 1 ) ).findAll( );
    }

    @Test
    void findById() {
        when( repository.findById( any( ) ) ).thenReturn(
                Optional.ofNullable( itemsList.get( 0 ) ) );
        Hero task = service.findById( 1L );
        Assertions.assertEquals( task, itemsList.get( 0 ) );
        verify( repository, times( 1 ) ).findById( any( ) );
    }

    @Test
    void delete() {
        doNothing( ).when( repository ).deleteById( any( ) );
        service.delete( 1L );
        verify( repository, times( 1 ) ).deleteById( any( ) );
    }

    @Test
    void update() {
        Hero item = itemsList.get( 0 );
        item.setName( "Test" );
        item.setCreatedAt( LocalDateTime.now( ) );

        when( repository.save( item ) ).thenReturn( item );
        Hero response = service.update( item );
        Assertions.assertEquals( item.getId(), response.getId() );

        verify( repository, times( 1 ) ).save( any( ) );
    }
}