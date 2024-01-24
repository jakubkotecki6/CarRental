package pl.sda.carrental.service;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.carrental.exceptionHandling.ObjectNotFoundInRepositoryException;
import pl.sda.carrental.model.*;
import pl.sda.carrental.model.DTO.ReservationDTO;
import pl.sda.carrental.model.enums.Status;
import pl.sda.carrental.repository.BranchRepository;
import pl.sda.carrental.repository.CarRepository;
import pl.sda.carrental.repository.ClientRepository;
import pl.sda.carrental.repository.ReservationRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@SpringBootTest
class ReservationServiceTest {

    @Mock
    private CarRepository carRepositoryMock;
    @Mock
    private ClientRepository clientRepositoryMock;
    @Mock
    private BranchRepository branchRepositoryMock;
    @Mock
    private ReservationRepository reservationRepositoryMock;
    @Mock
    private RevenueService revenueServiceMock;
    @InjectMocks
    private ReservationService reservationService;


    @Test
    void shouldGetAllReservations() {
        // given
        Reservation reservation1 = createReservation(1L, new BigDecimal(100));
        Reservation reservation2 = createReservation(2L, new BigDecimal(150));

        when(reservationRepositoryMock.findAll()).thenReturn(Arrays.asList(reservation1, reservation2));

        // when
        List<ReservationDTO> result = reservationService.getAllReservations();

        // then
        assertThat(2).isEqualTo(result.size());
    }

    @Test
    void shouldSaveReservation() {
        //given
        ReservationDTO reservationDto = createReservationDTO(1L);
        Branch branch = createBranch(1L);
        Car car = createCar(1L, "Sedan", new BigDecimal(100), "RED", "Volvo");
        Client client = createClient(1L, "Address 1", "Name 1", "Email1", "Surname 1");

        when(branchRepositoryMock.findById(1L)).thenReturn(Optional.of(branch));
        when(carRepositoryMock.findById(1L)).thenReturn(Optional.of(car));
        when(clientRepositoryMock.findById(1L)).thenReturn(Optional.of(client));

        //when
        reservationService.saveReservation(reservationDto);

        //then
        verify(carRepositoryMock).findById(1L);
        verify(branchRepositoryMock, times(2)).findById(1L);
        verify(clientRepositoryMock).findById(1L);
        verify(revenueServiceMock).updateRevenue(1L, new BigDecimal(200));

        ArgumentCaptor<Reservation> captor = ArgumentCaptor.forClass(Reservation.class);
        verify(reservationRepositoryMock).save(captor.capture());
        Reservation result = captor.getValue();

        assertThat(result.getEndDate()).isEqualTo("2023-11-22");
        assertThat(result.getStartDate()).isEqualTo("2023-11-20");
        assertThat(result.getPrice().intValue()).isEqualTo(200);
    }

    @Test
    void shouldDeleteReservationById() {
        //given
        Reservation reservation = createReservation(1L, new BigDecimal(100));
        when(reservationRepositoryMock.findById(anyLong())).thenReturn(Optional.of(reservation));

        //when
        reservationService.deleteReservationById(1L);

        //then
        verify(reservationRepositoryMock).delete(reservation);
    }

    @Test
    void shouldNotDeleteReservationById() {
        //given
        when(reservationRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());

        //when
        ThrowableAssert.ThrowingCallable callable = () -> reservationService.deleteReservationById(1L);

        //then
        assertThatThrownBy(callable)
                .isInstanceOf(ObjectNotFoundInRepositoryException.class)
                .hasMessage("No reservation under ID #1");

    }

    private Reservation createReservation(Long id, BigDecimal price) {
        return new Reservation()
                .withReservationId(id)
                .withCar(new Car())
                .withClient(new Client())
                .withPrice(price)
                .withStartBranch(new Branch())
                .withEndBranch(new Branch())
                .withStartDate(LocalDate.of(2024, 01, 01))
                .withEndDate(LocalDate.of(2024, 01, 05));
    }

    private Branch createBranch(Long id) {
        return new Branch()
                .withBranchId(id)
                .withAddress("Address 1")
                .withCars(new HashSet<>())
                .withClients(new HashSet<>())
                .withEmployees(new HashSet<>())
                .withName("Name 1")
                .withBranchId(1L)
                .withRevenue(new Revenue()
                        .withRevenueId(1L)
                        .withTotalAmount(new BigDecimal(100)))
                .withManagerId(1L);
    }

    private Car createCar(Long id, String bodyStyle, BigDecimal price, String color, String make) {
        return new Car()
                .withCarId(id)
                .withBodyStyle(bodyStyle)
                .withPrice(price)
                .withColour(color)
                .withMake(make)
                .withBranch(createBranch(1L))
                .withMileage(0)
                .withYear(2005)
                .withStatus(Status.AVAILABLE);
    }

    private Client createClient(Long id, String address, String name, String email, String surname) {
        return new Client()
                .withClientId(id)
                .withAddress(address)
                .withName(name)
                .withEmail(email)
                .withBranch(new Branch())
                .withSurname(surname);
    }


    private ReservationDTO createReservationDTO(Long id) {
        return new ReservationDTO(
                id,
                1L,
                1L,
                LocalDate.of(2023, 11, 20),
                LocalDate.of(2023, 11, 22),
                1L,
                1L,
                null,
                null
        );
    }


}