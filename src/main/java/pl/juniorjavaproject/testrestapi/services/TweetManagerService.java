package pl.juniorjavaproject.testrestapi.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import pl.juniorjavaproject.testrestapi.dto.TweetDTO;
import pl.juniorjavaproject.testrestapi.exceptions.ElementNotFoundException;

import javax.validation.Valid;
import java.net.URI;

@Service
public class TweetManagerService {

    private final TweetService tweetService;


    public TweetManagerService(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    public ResponseEntity<TweetDTO> create(@Valid @RequestBody TweetDTO tweetDTO, BindingResult result) {
        if(result.hasErrors()){
            //todo
        }
        return ResponseEntity.created(URI.create("/api/tweets/" + tweetService.read(tweetDTO))).build();
    }

    public ResponseEntity<TweetDTO> read(@PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            // todo
        }
        TweetDTO tweetDTO = tweetService.read(id);
        if (tweetDTO != null) {
            return ResponseEntity.ok(tweetDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<TweetDTO> update(@PathVariable Long id, @Valid @RequestBody TweetDTO tweetDTO)
            throws ElementNotFoundException {
        TweetDTO updatedTweetDTO = tweetService.update(id, tweetDTO);
        return ResponseEntity.ok(updatedTweetDTO);
    }

    public ResponseEntity<Void> delete(@PathVariable Long id) throws ElementNotFoundException {
        tweetService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
