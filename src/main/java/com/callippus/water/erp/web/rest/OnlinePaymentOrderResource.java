package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.ConfigurationDetails;
import com.callippus.water.erp.domain.OnlinePaymentOrder;
import com.callippus.water.erp.repository.ConfigurationDetailsRepository;
import com.callippus.water.erp.repository.OnlinePaymentOrderRepository;
import com.callippus.water.erp.service.BillingService;
import com.callippus.water.erp.service.OnlinePaymentService;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.callippus.water.erp.web.rest.util.PaginationUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing OnlinePaymentOrder.
 */
@RestController
@RequestMapping("/api")
public class OnlinePaymentOrderResource {

	private final Logger log = LoggerFactory
			.getLogger(OnlinePaymentOrderResource.class);

	@Inject
	private OnlinePaymentOrderRepository onlinePaymentOrderRepository;

	@Inject
	private OnlinePaymentService onlinePaymentService;

	/**
	 * POST /onlinePaymentOrders -> Create a new onlinePaymentOrder.
	 */
	@RequestMapping(value = "/onlinePaymentOrders", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<OnlinePaymentOrder> createOnlinePaymentOrder(
			@RequestBody OnlinePaymentOrder onlinePaymentOrder)
			throws URISyntaxException, Exception {
		log.debug("REST request to save OnlinePaymentOrder : {}",
				onlinePaymentOrder);

		if (onlinePaymentOrder.getId() != null) {
			return ResponseEntity
					.badRequest()
					.headers(
							HeaderUtil
									.createFailureAlert("onlinePaymentOrder",
											"idexists",
											"A new onlinePaymentOrder cannot already have an ID"))
					.body(null);
		}

		String redirectUrl = onlinePaymentService
				.processOrder(onlinePaymentOrder);
		if (redirectUrl.startsWith("http://")) {
			HttpHeaders headers = new HttpHeaders();

			return new ResponseEntity<OnlinePaymentOrder>(null, headers,
					HttpStatus.FOUND);
		} else {
			return ResponseEntity
					.ok()
					.headers(
							HeaderUtil
									.createAlert(
											"Error transacting with Unified Payment Portal",
											redirectUrl)).body(onlinePaymentOrder);
		}
	}

	/**
	 * PUT /onlinePaymentOrders -> Updates an existing onlinePaymentOrder.
	 */
	@RequestMapping(value = "/onlinePaymentOrders", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<OnlinePaymentOrder> updateOnlinePaymentOrder(
			@RequestBody OnlinePaymentOrder onlinePaymentOrder)
			throws URISyntaxException, Exception {
		log.debug("REST request to update OnlinePaymentOrder : {}",
				onlinePaymentOrder);
		if (onlinePaymentOrder.getId() == null) {
			return createOnlinePaymentOrder(onlinePaymentOrder);
		}
		OnlinePaymentOrder result = onlinePaymentOrderRepository
				.save(onlinePaymentOrder);
		return ResponseEntity
				.ok()
				.headers(
						HeaderUtil.createEntityUpdateAlert(
								"onlinePaymentOrder", onlinePaymentOrder
										.getId().toString())).body(result);
	}

	/**
	 * GET /onlinePaymentOrders -> get all the onlinePaymentOrders.
	 */
	@RequestMapping(value = "/onlinePaymentOrders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<OnlinePaymentOrder>> getAllOnlinePaymentOrders(
			Pageable pageable) throws URISyntaxException {
		log.debug("REST request to get a page of OnlinePaymentOrders");
		Page<OnlinePaymentOrder> page = onlinePaymentOrderRepository
				.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(
				page, "/api/onlinePaymentOrders");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET /onlinePaymentOrders/:id -> get the "id" onlinePaymentOrder.
	 */
	@RequestMapping(value = "/onlinePaymentOrders/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<OnlinePaymentOrder> getOnlinePaymentOrder(
			@PathVariable Long id) {
		log.debug("REST request to get OnlinePaymentOrder : {}", id);
		OnlinePaymentOrder onlinePaymentOrder = onlinePaymentOrderRepository
				.findOne(id);
		return Optional.ofNullable(onlinePaymentOrder)
				.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * DELETE /onlinePaymentOrders/:id -> delete the "id" onlinePaymentOrder.
	 */
	@RequestMapping(value = "/onlinePaymentOrders/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Void> deleteOnlinePaymentOrder(@PathVariable Long id) {
		log.debug("REST request to delete OnlinePaymentOrder : {}", id);
		onlinePaymentOrderRepository.delete(id);
		return ResponseEntity
				.ok()
				.headers(
						HeaderUtil.createEntityDeletionAlert(
								"onlinePaymentOrder", id.toString())).build();
	}
}
