package org.example.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoutonTest {

    private Plateau plateau;
    private Mouton mouton;

    @BeforeEach
    void setUp() {
        plateau = new Plateau();
        mouton = plateau.getMouton();
    }

    @AfterEach
    void tearDown() {
        mouton = null;
        plateau = null;
    }

    @Test
    void test1() {
        plateau.getCase(1,1).setType(Element.Cactus);
        mouton.mange();
        assertEquals(1,mouton.getNbCase());
    }

    @Test
    void test2() {
        plateau.getCase(1,1).setType(Element.Herbe);
        mouton.mange();
        assertEquals(2,mouton.getNbCase());
    }

    @Test
    void test3() {
        plateau.getCase(1,1).setType(Element.Marguerite);
        mouton.mange();
        assertEquals(4,mouton.getNbCase());
    }

    @Test
    void test4() {
        plateau.getCase(1,1).setType(Element.Roche);
        assertFalse(mouton.mange());
    }

    @Test
    void test5() {
        plateau.getCase(1,1).setType(Element.Herbe);
        assertTrue(mouton.mange());
    }

    @Test
    void test6() {
        plateau.getCase(1,1).setType(Element.Marguerite);
        assertTrue(mouton.mange());
    }

    @Test
    void test7() {
        plateau.getCase(1,1).setType(Element.Cactus);
        assertTrue(mouton.mange());
    }

    @Test
    void test8() {
        plateau.getCase(1,1).setType(Element.Marguerite);
        mouton.mange();
        mouton.mange();
        mouton.mange();
        mouton.mange();
        mouton.mange();
        assertEquals(5,mouton.getNourriture().size());
    }

    @Test
    void test9() {
        plateau.getCase(1,1).setType(Element.Marguerite);
        mouton.mange();
        mouton.mange();
        plateau.getCase(1,1).setType(Element.Herbe);
        mouton.mange();
        assertEquals(3,mouton.getNourriture().size());
    }

    @Test
    void testDijkstraMouton(){
        plateau.setCaseFinal(plateau.getCase(1,0));

        plateau.getCase(1,2).setType(Element.Roche);
        plateau.getCase(2,2).setType(Element.Roche);
        plateau.getCase(4,2).setType(Element.Roche);
        plateau.getCase(5,2).setType(Element.Roche);
        plateau.getCase(6,2).setType(Element.Roche);
        plateau.getCase(7,2).setType(Element.Roche);

        plateau.getCase(7,3).setType(Element.Roche);

        plateau.getCase(2,4).setType(Element.Roche);
        plateau.getCase(3,4).setType(Element.Roche);
        plateau.getCase(4,4).setType(Element.Roche);
        plateau.getCase(5,4).setType(Element.Roche);
        plateau.getCase(6,4).setType(Element.Roche);
        plateau.getCase(7,4).setType(Element.Roche);

        mouton.deplace(8,5);
        plateau.getLoup().deplace(6,3);

        assertEquals(13,mouton.dijkstra().size());
    }

    @Test
    void testAStarMouton(){
        plateau.setCaseFinal(plateau.getCase(1,0));

        plateau.getCase(1,2).setType(Element.Roche);
        plateau.getCase(2,2).setType(Element.Roche);
        plateau.getCase(4,2).setType(Element.Roche);
        plateau.getCase(5,2).setType(Element.Roche);
        plateau.getCase(6,2).setType(Element.Roche);
        plateau.getCase(7,2).setType(Element.Roche);

        plateau.getCase(7,3).setType(Element.Roche);

        plateau.getCase(2,4).setType(Element.Roche);
        plateau.getCase(3,4).setType(Element.Roche);
        plateau.getCase(4,4).setType(Element.Roche);
        plateau.getCase(5,4).setType(Element.Roche);
        plateau.getCase(6,4).setType(Element.Roche);
        plateau.getCase(7,4).setType(Element.Roche);

        mouton.deplace(8,5);
        plateau.getLoup().deplace(6,3);

        assertEquals(13,mouton.AStar().size());
    }

    @Test
    void testFourmiMouton() {
        plateau.setCaseFinal(plateau.getCase(1, 0));

        plateau.getCase(1, 2).setType(Element.Roche);
        plateau.getCase(2, 2).setType(Element.Roche);
        plateau.getCase(4, 2).setType(Element.Roche);
        plateau.getCase(5, 2).setType(Element.Roche);
        plateau.getCase(6, 2).setType(Element.Roche);
        plateau.getCase(7, 2).setType(Element.Roche);

        plateau.getCase(7, 3).setType(Element.Roche);

        plateau.getCase(2, 4).setType(Element.Roche);
        plateau.getCase(3, 4).setType(Element.Roche);
        plateau.getCase(4, 4).setType(Element.Roche);
        plateau.getCase(5, 4).setType(Element.Roche);
        plateau.getCase(6, 4).setType(Element.Roche);
        plateau.getCase(7, 4).setType(Element.Roche);

        mouton.deplace(8, 5);
        plateau.getLoup().deplace(6, 3);

        assertEquals(13, mouton.fourmi(1000).size());
    }

    @Test
    void testDijkstraLoup(){
        plateau.setCaseFinal(plateau.getCase(1,0));

        plateau.getCase(2,1).setType(Element.Roche);

        plateau.getCase(4,2).setType(Element.Roche);
        plateau.getCase(7,2).setType(Element.Roche);

        plateau.getCase(2,3).setType(Element.Roche);
        plateau.getCase(3,3).setType(Element.Roche);
        plateau.getCase(4,3).setType(Element.Roche);
        plateau.getCase(5,3).setType(Element.Roche);

        plateau.getCase(6,4).setType(Element.Roche);
        plateau.getCase(7,4).setType(Element.Roche);

        plateau.getCase(2,5).setType(Element.Roche);
        plateau.getCase(4,5).setType(Element.Roche);

        mouton.deplace(8,5);
        plateau.getLoup().deplace(1,2);

        assertEquals(11,plateau.getLoup().dijkstra().size());
    }

    @Test
    void testAStarLoup(){
        plateau.setCaseFinal(plateau.getCase(1,0));

        plateau.getCase(2,1).setType(Element.Roche);

        plateau.getCase(4,2).setType(Element.Roche);
        plateau.getCase(7,2).setType(Element.Roche);

        plateau.getCase(2,3).setType(Element.Roche);
        plateau.getCase(3,3).setType(Element.Roche);
        plateau.getCase(4,3).setType(Element.Roche);
        plateau.getCase(5,3).setType(Element.Roche);

        plateau.getCase(6,4).setType(Element.Roche);
        plateau.getCase(7,4).setType(Element.Roche);

        plateau.getCase(2,5).setType(Element.Roche);
        plateau.getCase(4,5).setType(Element.Roche);

        mouton.deplace(8,5);
        plateau.getLoup().deplace(1,2);

        assertEquals(11,plateau.getLoup().AStar().size());
    }

    @Test
    void testFourmiLoup(){
        plateau.setCaseFinal(plateau.getCase(1,0));

        plateau.getCase(2,1).setType(Element.Roche);

        plateau.getCase(4,2).setType(Element.Roche);
        plateau.getCase(7,2).setType(Element.Roche);

        plateau.getCase(2,3).setType(Element.Roche);
        plateau.getCase(3,3).setType(Element.Roche);
        plateau.getCase(4,3).setType(Element.Roche);
        plateau.getCase(5,3).setType(Element.Roche);

        plateau.getCase(6,4).setType(Element.Roche);
        plateau.getCase(7,4).setType(Element.Roche);

        plateau.getCase(2,5).setType(Element.Roche);
        plateau.getCase(4,5).setType(Element.Roche);

        mouton.deplace(8,5);
        plateau.getLoup().deplace(1,2);

        assertEquals(11,plateau.getLoup().fourmi(1000).size());
    }



}